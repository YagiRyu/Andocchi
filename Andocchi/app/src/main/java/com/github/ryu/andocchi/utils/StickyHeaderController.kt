package com.github.ryu.andocchi.utils

import com.airbnb.epoxy.Typed2EpoxyController
import com.github.ryu.andocchi.ItemSectionListBindingModel_
import com.github.ryu.andocchi.itemChildNodeList
import com.github.ryu.andocchi.itemNodeList
import com.github.ryu.andocchi.model.Path

class StickyHeaderController() : Typed2EpoxyController<List<Path>, List<Path>>() {

    lateinit var listener: OnClickListener

    interface OnClickListener {
        fun onSelected(id: Int)
    }

    fun click(listener: OnClickListener) {
        this.listener = listener
    }

    override fun isStickyHeader(position: Int): Boolean {
        return adapter.getModelAtPosition(position)::class == ItemSectionListBindingModel_::class
    }

    override fun buildModels(headers: List<Path>, contents: List<Path>) {
        var i = 0

        while (i < headers.size) {
            ItemSectionListBindingModel_()
                .id(i)
                .section(headers[i])
                .title(headers[i].title)
                .addTo(this)

            if (contents[i].sections?.size != null) {
                contents[i].sections?.get(0)?.nodes?.forEachIndexed { index, node ->
                    itemNodeList {
                        id(i)
                        node(node)
                        if (!node.childNodes.isNullOrEmpty()) {
                            node.childNodes.forEach {
                                itemChildNodeList {
                                    id(i)
                                    childNode(it)
                                }
                            }
                        }
                    }
                }
            }
            i++
        }
    }
}
