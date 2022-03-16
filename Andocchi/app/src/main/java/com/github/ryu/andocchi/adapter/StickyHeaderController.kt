package com.github.ryu.andocchi.adapter

import com.airbnb.epoxy.Typed2EpoxyController
import com.github.ryu.andocchi.*
import com.github.ryu.andocchi.model.Path

class StickyHeaderController(
    private val skillList: MutableList<String>,
    private val onClick: (position: Int, title: String) -> Unit
) : Typed2EpoxyController<List<Path>, List<Path>>() {

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
                        isFinished(node.id.toString() in skillList)
                        click { v ->
                            onClick(node.id, node.title!!)
                            v.setBackgroundColor(R.drawable.background)
                        }
                        if (!node.childNodes.isNullOrEmpty()) {
                            node.childNodes.forEach {
                                itemChildNodeList {
                                    id(i)
                                    childNode(it)
                                    isFinished(it.id.toString() in skillList)
                                    click { v ->
                                        onClick(it.id!!, node.title!!)
                                        v.setBackgroundColor(R.drawable.background)
                                    }
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
