package com.example.testidp.features.animations.recycleranimations

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.example.testidp.R

class CustomItemAnimator : DefaultItemAnimator() {

    override fun animateRemove(holder: RecyclerView.ViewHolder): Boolean {
        val anim = AnimationUtils.loadAnimation(
            holder.itemView.context,
            R.anim.remove_item_animation
        )
        holder.itemView.animation = anim
        return super.animateRemove(holder)
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {
        val anim = AnimationUtils.loadAnimation(
            holder.itemView.context,
            R.anim.add_item_anim
        )
        holder.itemView.animation = anim
        return super.animateAdd(holder)
    }
}