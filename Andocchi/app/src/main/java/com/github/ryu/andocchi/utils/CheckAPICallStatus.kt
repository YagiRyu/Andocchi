package com.github.ryu.andocchi.utils

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import retrofit2.Response

sealed class Future<out T> {
    object Proceeding : Future<Nothing>()

    data class Success<out T>(val value: T) : Future<T>()

    data class Failure(val error: Throwable) : Future<Nothing>()
}

inline fun <reified T : Any> apiFlow(crossinline call: suspend () -> Response<T>): Flow<Future<T>> =
    flow<Future<T>> {
        val response = call()

        if (response.isSuccessful) emit(Future.Success(response.body()!!))
        else throw HttpException(response)
    }.catch {
        emit(Future.Failure(it))
    }.onStart {
        emit(Future.Proceeding)
    }.flowOn(Dispatchers.IO)


suspend fun <T> retryOrNull(
    retries: Int,
    intervalMills: Long,
    block: suspend () -> T
): T? {
    repeat(retries) {
        try {
            return block()
        } catch (e: CancellationException) {
            // キャンセル時は再スローする
            throw e
        } catch (e: Throwable) {
            delay(intervalMills)
        }
    }
    return null
}
