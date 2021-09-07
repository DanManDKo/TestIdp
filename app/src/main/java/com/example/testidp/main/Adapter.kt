package com.example.testidp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testidp.R
import com.example.testidp.enums.MainItemType
import com.example.testidp.utils.normalize

class Adapter(private val onItemClickListener: (MainItemType) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val items = listOf(
        MainItemType.MAP,
        MainItemType.DELEGATE
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var textView: TextView? = view.findViewById(R.id.textView)

        fun bind(type: MainItemType) {
            textView?.text = type.name.normalize()
            itemView.setOnClickListener {
                onItemClickListener.invoke(type)
            }
        }
    }
}