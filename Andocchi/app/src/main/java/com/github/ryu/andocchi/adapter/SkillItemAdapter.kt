package com.github.ryu.andocchi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.databinding.SkillItemBinding
import com.github.ryu.andocchi.model.Path

class SkillItemAdapter(
    private val list: List<Path>,
) : RecyclerView.Adapter<SkillItemAdapter.SkillItemViewHolder>() {

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

        holder.title.text = skill.title
    }

    override fun getItemCount() = list.size
}
