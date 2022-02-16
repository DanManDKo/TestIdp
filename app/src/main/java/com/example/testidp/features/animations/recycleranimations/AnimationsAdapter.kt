package com.example.testidp.features.animations.recycleranimations

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testidp.R
import com.example.testidp.utils.inflate

class AnimationsAdapter : RecyclerView.Adapter<AnimationsAdapter.ViewHolder>() {

    private val items = mutableListOf(
        AnimationsItem("Title 1", "Description 1"),
        AnimationsItem("Title 2", "Description 2"),
        AnimationsItem("Title 3", "Description 3"),
        AnimationsItem("Title 4", "Description 5"),
        AnimationsItem("Title 6", "Description 6")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_animations))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addNewItemToTheEnd() {
        val index = items.size + 1
        items.add(AnimationsItem("Title $index", "Description $index"))
        notifyItemInserted(items.lastIndex)
    }

    fun addNewItemInTheMiddle() {
        val index = if (items.size == 1) {
            1
        } else {
            items.size / 2
        }
        items.add(index, AnimationsItem("Title $index", "Description $index"))
        notifyItemInserted(index)
    }

    fun removeItemFromTheEnd() {
        if (items.isEmpty()) return
        val index = items.lastIndex
        items.removeLast()
        notifyItemRemoved(index)
    }

    fun removeItemFromTheMiddle() {
        if (items.isEmpty()) return
        val index = items.size / 2
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    fun insertRange() {
        val range = listOf(
            AnimationsItem("Title range 1", "Description range 1"),
            AnimationsItem("Title range 2", "Description range 2"),
            AnimationsItem("Title range 3", "Description range 3"),
        )
        val index = if (items.isEmpty()) {
            0
        } else {
            items.size / 2
        }
        items.addAll(index, range)
        notifyItemRangeInserted(index, range.count())
    }

    fun removeRange() {
        if (items.size < 5) return
        val indexFrom = items.lastIndex - 4
        val indexTo = items.lastIndex - 1
        val range = items.subList(indexFrom, indexTo).clear()
        notifyItemRangeRemoved(indexFrom, indexTo)

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var title: TextView? = view.findViewById(R.id.tvTitle)
        private var description: TextView? = view.findViewById(R.id.tvDescription)

        fun bind(item: AnimationsItem) {
            title?.text = item.title
            description?.text = item.description
        }
    }
}