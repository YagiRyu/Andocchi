package com.github.ryu.andocchi.ui.tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ryu.andocchi.R

@Composable
fun FirstScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.character_01),
            contentDescription = "first tutorial image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        
        Text(text = stringResource(id = R.string.tutorial_first_text),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green
        )
    }
}

@Preview
@Composable
fun SecondScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.character_05),
            contentDescription = "first tutorial image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(text = stringResource(id = R.string.tutorial_second_text),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green
        )

    }
}

@Composable
fun ThirdScreen(navigation: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.andocchi_tutorial),
            contentDescription = "first tutorial image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(text = stringResource(id = R.string.tutorial_third_text),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green
        )
        
        Button(onClick = { navigation() }, modifier = Modifier.padding(10.dp)) {
            Text(text = "Go !", fontSize = 20.sp)
        }

    }
}
