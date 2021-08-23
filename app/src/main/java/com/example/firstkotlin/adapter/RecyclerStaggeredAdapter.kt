package com.example.firstkotlin.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ItemRecyclerGridBinding
import com.example.firstkotlin.model.LifeItem

/**
 * Created on 2020/6/1.
 *
 */
class RecyclerStaggeredAdapter(context: Context, private val infos: MutableList<LifeItem>):
    RecyclerBaseAdapter<RecyclerView.ViewHolder>(context) {
    private lateinit var binding: ItemRecyclerGridBinding

    override fun getItemCount(): Int = infos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val view = inflater.inflate(R.layout.item_recycler_grid, parent, false)
        binding = ItemRecyclerGridBinding.inflate(inflater, parent, false)
        return ItemHolderCon2(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val info = infos[position]

        // 方式一：普通方式获取viewholder，然后设置参数
//        val vh = holder as ItemHolder
//        vh.iv_pic.setImageResource(info.picId)
//        vh.tv_title.text = info.title

        // 方式二：使用插件LayoutContainer，已废弃
//        (holder as ItemHolderCon).bind(info)

        // 方式三：视图绑定
        (holder as ItemHolderCon2).bind(info)
    }

    inner class ItemHolder(view: View): RecyclerView.ViewHolder(view) {
        var ll_item = view.findViewById<LinearLayout>(R.id.ll_item)
        var iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
        var tv_title = view.findViewById<TextView>(R.id.tv_title)
    }

//    class ItemHolderCon(override val containerView: View):
//        RecyclerView.ViewHolder(containerView), LayoutContainer {
//        fun bind(item: LifeItem) {
//            iv_pic.setImageResource(item.picId)
//            tv_title.text = item.title
//        }
//    }

    inner class ItemHolderCon2(containerView: View):
        RecyclerView.ViewHolder(containerView) {
        fun bind(item: LifeItem) {
            binding.ivPic.setImageResource(item.picId)
            binding.tvTitle.text = item.title
        }
    }
 }