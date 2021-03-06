package com.example.testidp.features.recycler.drag

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.testidp.R
import com.example.testidp.utils.inflate
import java.util.*

class DragAndDropAdapter : RecyclerView.Adapter<DragAndDropAdapter.ViewHolder>(),
    DragAndDropHelper.OnItemMoveListener {

    private val items = LinkedList<DragAndDropItem>()

    private val dragAndDropHelper = DragAndDropHelper(this)
    private val touchHelper = ItemTouchHelper(dragAndDropHelper)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        touchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_recycler_drag))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwiped(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setItems(items: List<DragAndDropItem>) {
        this.items.clear()
        this.items.addAll(items)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var tvContent: TextView? = view.findViewById(R.id.tvContent)

        fun bind(item: DragAndDropItem) {
            tvContent?.text = item.content
        }
    }
}