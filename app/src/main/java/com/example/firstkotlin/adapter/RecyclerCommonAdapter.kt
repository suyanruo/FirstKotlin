package com.example.firstkotlin.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 2020/6/1.
 *
 */
class RecyclerCommonAdapter<T>(context: Context, private val layoutId: Int,
                               private val items: List<T>, val init:(View, T) -> Unit):
    RecyclerBaseAdapter<RecyclerCommonAdapter.ItemHolder<T>>(context) {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflater.inflate(layoutId, parent, false)
        return ItemHolder(view, init)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: ItemHolder<T> = holder as ItemHolder<T>
        vh.bind(items[position])
    }

    class ItemHolder<in T>(val view: View, val init:(View, T) -> Unit): RecyclerView.ViewHolder(view) {
        fun bind(item: T) {
            init(view, item)
        }
    }
}