package com.example.firstkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.firstkotlin.R
import com.example.firstkotlin.modle.RecyclerInfo
import com.example.firstkotlin.view.RecyclerCommonAdapter
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        rv_rec.layoutManager = StaggeredGridLayoutManager(3, LinearLayout.VERTICAL)
        var list = arrayOf(
            RecyclerInfo("1", R.drawable.dice_1),
            RecyclerInfo("2", R.drawable.dice_2),
            RecyclerInfo("3", R.drawable.dice_3),
            RecyclerInfo("4", R.drawable.dice_4))
        val adapter = RecyclerCommonAdapter(this,
            R.layout.item_recycler_grid, list.toList()
        ) { view, item ->
            val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
            val tv_title = view.findViewById<TextView>(R.id.tv_title)
            iv_pic.setImageResource(item.picId)
            tv_title.text = item.title
        }
        rv_rec.adapter = adapter
        rv_rec.itemAnimator = DefaultItemAnimator()
    }
}
