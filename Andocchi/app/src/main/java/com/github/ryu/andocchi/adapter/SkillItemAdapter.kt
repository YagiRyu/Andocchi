package com.github.ryu.andocchi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.model.Path
import com.github.ryu.andocchi.utils.createId

class SkillItemAdapter(
    private val list: MutableList<String>,
    private val path: List<Path>
) : RecyclerView.Adapter<SkillItemAdapter.SkillItemViewHolder>() {

    lateinit var listener: OnItemClickListener

    private val matchSectionTitle: MutableList<String> = mutableListOf()

    class SkillItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.skill_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.skill_item, parent, false)
        return SkillItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkillItemViewHolder, position: Int) {
        val skill = list[position]

        holder.title.text = skill

        holder.title.setOnClickListener {
            listener.onItemClickListener(position.createId(), skill)
        }
    }

    override fun getItemCount() = list.lastIndex

    interface OnItemClickListener {
        fun onItemClickListener(pathPosition: Int, skill: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
