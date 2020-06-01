package com.example.firstkotlin.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 2020/6/1.
 *
 */
abstract class RecyclerBaseAdapter<VH: RecyclerView.ViewHolder>(val context: Context):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    var itemClickListener: AdapterView.OnItemClickListener? = null
    var itemLongClickListener: AdapterView.OnItemLongClickListener? = null

    // 列表项个数
    abstract override fun getItemCount(): Int

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemId(position: Int): Long = position.toLong()

    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener) {
        this.itemClickListener = listener }

    fun setOnItemLongClickListener(listener: AdapterView.OnItemLongClickListener) {
        this.itemLongClickListener = listener }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onItemLongClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ): Boolean {
        TODO("Not yet implemented")
    }

}