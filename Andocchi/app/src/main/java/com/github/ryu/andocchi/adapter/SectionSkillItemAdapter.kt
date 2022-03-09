package com.github.ryu.andocchi.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.model.ChildNode
import com.github.ryu.andocchi.model.Node

class SectionSkillItemAdapter(
    private val list: List<Node>
) : RecyclerView.Adapter<SectionSkillItemAdapter.SectionSkillItemViewHolder>() {

    lateinit var listener: OnItemClickListener

    class SectionSkillItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: ConstraintLayout = itemView.findViewById(R.id.section_skill_item_constraint)
        val title: TextView = itemView.findViewById(R.id.skill_item)
        val priorityText: TextView = itemView.findViewById(R.id.skill_item_priority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionSkillItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.section_skill_item, parent, false)
        return SectionSkillItemViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SectionSkillItemViewHolder, position: Int) {
        val skill = list[position]
        val priority = list[position].priority
        val cardView = holder.cardView

        when (priority) {
            1 -> cardView.setBackgroundResource(R.drawable.priority_one_gradient)
            2 -> cardView.setBackgroundResource(R.drawable.priority_two_gradient)
            3 -> cardView.setBackgroundResource(R.drawable.priority_three_gradient)
            4 -> cardView.setBackgroundResource(R.drawable.priority_four_gradient)
            5 -> cardView.setBackgroundResource(R.drawable.priority_five_gradient)
        }

        holder.priorityText.text = "Priority " + priority.toString()
        holder.title.text = skill.title

        holder.cardView.setOnClickListener {
            listener.onItemClickListener(it, position, list[position].title.toString())
        }

//        if (skill.isJetpack!!) {
//            holder.cardView.setBackgroundResource(R.drawable.is_jetpack_gradient_color)
//        }
    }

    override fun getItemCount() = list.size

    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int, clickedText: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
