package com.github.ryu.andocchi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.ryu.andocchi.R
import com.github.ryu.andocchi.model.ChildNode

class NodeSkillItemAdapter(
    private val list: List<ChildNode>
) : RecyclerView.Adapter<NodeSkillItemAdapter.NodeSkillItemViewHolder>() {

    class NodeSkillItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: ConstraintLayout = itemView.findViewById(R.id.node_skill_item_constraint)
        val title: TextView = itemView.findViewById(R.id.skill_item)
        val priorityText: TextView = itemView.findViewById(R.id.skill_item_priority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeSkillItemAdapter.NodeSkillItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.node_skill_item, parent, false)
        return NodeSkillItemAdapter.NodeSkillItemViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NodeSkillItemAdapter.NodeSkillItemViewHolder, position: Int) {
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
    }

    override fun getItemCount() = list.size
}