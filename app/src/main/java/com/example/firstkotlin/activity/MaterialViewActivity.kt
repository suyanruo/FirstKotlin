package com.example.firstkotlin.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstkotlin.R
import com.example.firstkotlin.modle.LifeItem
import com.example.firstkotlin.adapter.RecyclerCommonAdapter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_material_view.*
import kotlinx.android.synthetic.main.life_pay.*
import kotlinx.android.synthetic.main.toolbar_collapse.*
import kotlinx.android.synthetic.main.toolbar_expand.*
import org.jetbrains.anko.px2dip

/**
 * ref: https://github.com/aqi00/kotlin
 */
class MaterialViewActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    private var mMaskColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_view)

        setSupportActionBar(toolbar)

        //第一种方式：使用采取了LayoutContainer的适配器
        //rv_content.adapter = RecyclerLifeAdapter(this, LifeItem.default)
        //第二种方式：使用把三类可变要素抽象出来的适配器
        rv_content.layoutManager = GridLayoutManager(this, 4)
        var list = makeArrayList()
        val adapter = RecyclerCommonAdapter(
            this,
            R.layout.item_recycler_grid, LifeItem.default
        ) { view, item ->
            val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
            val tv_title = view.findViewById<TextView>(R.id.tv_title)
            iv_pic.setImageResource(item.picId)
            tv_title.text = item.title
        }
        rv_content.adapter = adapter
        rv_content.itemAnimator = DefaultItemAnimator()

        mMaskColor = ContextCompat.getColor(this, R.color.blue_black)
        //给控件abl_bar注册一个位置偏移的监听器
        app_bar.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val offset = Math.abs(verticalOffset)
        val total = appBarLayout.totalScrollRange
        val alphaIn = (px2dip(offset) * 2).toInt()
        val alphaOut = if (200 - alphaIn < 0) 0 else 200 - alphaIn

        //计算淡入时候的遮罩透明度
        val maskColorIn = Color.argb(alphaIn, Color.red(mMaskColor),
            Color.green(mMaskColor), Color.blue(mMaskColor))
        //工具栏下方的频道布局要加速淡入或者淡出
        val maskColorInDouble = Color.argb(alphaIn * 2, Color.red(mMaskColor),
            Color.green(mMaskColor), Color.blue(mMaskColor))
        //计算淡出时候的遮罩透明度
        val maskColorOut = Color.argb(alphaOut * 3, Color.red(mMaskColor),
            Color.green(mMaskColor), Color.blue(mMaskColor))
        if (offset <= total.times(0.45)) { //偏移量小于一半，则显示展开时候的工具栏
            tl_expand.visibility = View.VISIBLE
            tl_collapse.visibility = View.GONE
            v_expand_mask.setBackgroundColor(maskColorInDouble)
        } else { //偏移量大于一半，则显示缩小时候的工具栏
            tl_expand.visibility = View.GONE
            tl_collapse.visibility = View.VISIBLE
            v_collapse_mask.setBackgroundColor(maskColorOut)
        }
        v_pay_mask.setBackgroundColor(maskColorIn)
    }

//    private fun showOrHideFloatingButton() {
//        if (fab_btn.isOrWillBeHidden) {
//            fab_btn.show()
//            Snackbar.make(cl_material_view, "tip bar", Snackbar.LENGTH_SHORT).show()
//        } else {
//            fab_btn.hide()
//        }
//    }

    private fun makeArrayList(): List<LifeItem> {
        return arrayOf(
            LifeItem("1", R.drawable.dice_1),
            LifeItem("2", R.drawable.dice_2),
            LifeItem("3", R.drawable.dice_3),
            LifeItem("4", R.drawable.dice_4),
            LifeItem("5", R.drawable.dice_5),
            LifeItem("6", R.drawable.dice_6),
            LifeItem("7", R.drawable.dice_4),
            LifeItem("8", R.drawable.dice_4)).toList()
    }
}
