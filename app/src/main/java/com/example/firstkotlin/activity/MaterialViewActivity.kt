package com.example.firstkotlin.activity

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.*
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstkotlin.R
import com.example.firstkotlin.adapter.RecyclerCommonAdapter
import com.example.firstkotlin.databinding.ActivityMaterialViewBinding
import com.example.firstkotlin.model.LifeItem
import com.google.android.material.appbar.AppBarLayout
import org.jetbrains.anko.*
import kotlin.math.abs


/**
 * ref: https://github.com/aqi00/kotlin
 */
class MaterialViewActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    private var mMaskColor: Int = 0
    private lateinit var binding: ActivityMaterialViewBinding
    private lateinit var popupWindow: PopupWindow
    private lateinit var progressDialog: ProgressDialog
    private var prog: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_material_view)
        binding = ActivityMaterialViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        initToolBar()
        initPopupWindow()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        //第一种方式：使用采取了LayoutContainer的适配器
        //rv_content.adapter = RecyclerLifeAdapter(this, LifeItem.default)
        //第二种方式：使用把三类可变要素抽象出来的适配器
        binding.rvContent.apply {
            layoutManager = GridLayoutManager(this@MaterialViewActivity, 4)
            val adapter = RecyclerCommonAdapter(
                this@MaterialViewActivity,
                R.layout.item_recycler_grid, LifeItem.default
            ) { view, item ->
                val iv_pic = view.findViewById<ImageView>(R.id.iv_pic)
                val tv_title = view.findViewById<TextView>(R.id.tv_title)
                iv_pic.setImageResource(item.picId)
                tv_title.text = item.title
            }
            this.adapter = adapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun initToolBar() {
        setSupportActionBar(binding.toolbar)

        mMaskColor = ContextCompat.getColor(this, R.color.blue_black)
        //给控件abl_bar注册一个位置偏移的监听器
        binding.appBar.addOnOffsetChangedListener(this)

        val ivPlusEx = binding.tlExpand.ivPlusEx
        ivPlusEx.setOnClickListener{
            popupWindow.contentView.measure(0, 0)
            popupWindow.showAsDropDown(ivPlusEx,
                (-(popupWindow.contentView.measuredWidth) * 1.5).toInt(),
                ivPlusEx.height)
            val lp: WindowManager.LayoutParams = window.attributes
            lp.alpha = 0.7f
            window.attributes = lp
        }

        val ivPlusCo = binding.tlCollapse.ivPlusCo
        ivPlusCo.setOnClickListener{
            popupWindow.contentView.measure(0, 0)
            popupWindow.showAsDropDown(ivPlusCo,
                (-(popupWindow.contentView.measuredWidth) * 1.5).toInt(),
                ivPlusCo.height)
            val lp: WindowManager.LayoutParams = window.attributes
            lp.alpha = 0.7f
            window.attributes = lp
        }
    }

    /**
     * PopupWindow: https://my.oschina.net/JiangTun/blog/2998576
     */
    private fun initPopupWindow() {
        val contentView = LayoutInflater.from(this).inflate(R.layout.layout_menu_item, null)
        popupWindow = PopupWindow(contentView, dip(150), ViewGroup.LayoutParams.WRAP_CONTENT, true)
        popupWindow.setBackgroundDrawable(ColorDrawable())

        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        popupWindow.setOnDismissListener {
            val lp: WindowManager.LayoutParams = window.attributes
            lp.alpha = 1f
            window.attributes = lp
        }
        val shopMenu = contentView.findViewById<TextView>(R.id.tv_shop)
        val clearMenu = contentView.findViewById<TextView>(R.id.tv_clear)
        shopMenu.setOnClickListener{
            startActivity(intentFor<StorageActivity>().clearTop())
        }
        clearMenu.setOnClickListener {
            popupWindow.dismiss()
            showProgressDialog()
        }
    }

    /**
     * 水平进度条loading，需要设置进度
     */
    private fun showProgressDialog() {
        progressDialog = progressDialog("Loading...", "Wait Please")
        progressDialog.show()
        // 多线程方式一：使用Thread + Handler
//            Thread {
//                prog = 0
//                while (prog < 100) {
//                    prog += 10
//                    handler.sendEmptyMessage(1)
//                    Thread.sleep(1000)
//                }
//                handler.sendEmptyMessage(2)
//            }.start()
        // 多线程方式二：使用doAsync + uiThread
        doAsync {
            for (pro in 0..10) {
                uiThread { refreshProgress(pro * 10) }
                Thread.sleep(1000)
            }
            uiThread { finishRefresh() }
        }

    }

    /**
     * 圆形loading
     */
    private fun showCircleProgressDialog() {
            val dialog = indeterminateProgressDialog("正在努力加载页面", "请稍等")
            dialog.show()
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val offset = abs(verticalOffset)
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
            binding.tlExpand.root.visibility = View.VISIBLE
            binding.tlCollapse.root.visibility = View.GONE
            binding.tlExpand.vExpandMask.setBackgroundColor(maskColorInDouble)
        } else { //偏移量大于一半，则显示缩小时候的工具栏
            binding.tlExpand.root.visibility = View.GONE
            binding.tlCollapse.root.visibility = View.VISIBLE
            binding.tlCollapse.vCollapseMask.setBackgroundColor(maskColorOut)
        }
        binding.lLifePay.vPayMask.setBackgroundColor(maskColorIn)
    }

//    private fun showOrHideFloatingButton() {
//        if (fab_btn.isOrWillBeHidden) {
//            fab_btn.show()
//            Snackbar.make(cl_material_view, "tip bar", Snackbar.LENGTH_SHORT).show()
//        } else {
//            fab_btn.hide()
//        }
//    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> refreshProgress(prog)
                2 -> finishRefresh()
            }
        }
    }

    private fun refreshProgress(pro: Int) {
        progressDialog.progress = pro
    }

    private fun finishRefresh() {
        progressDialog.dismiss()
    }
}
