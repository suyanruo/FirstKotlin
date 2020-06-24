package com.example.firstkotlin.fragment

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.R
import com.example.firstkotlin.activity.*
import com.example.firstkotlin.adapter.RecyclerCommonAdapter
import com.example.firstkotlin.base.BaseFragment
import com.example.firstkotlin.model.LifeItem
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

/**
 * Created on 2020/6/2.
 *
 */
class HomeFragment: BaseFragment() {
    private lateinit var btnRoll: Button
    private lateinit var btnDataBind: Button
    private lateinit var btnMaterial: Button
    private lateinit var btnStorage: Button
    private lateinit var btnLiveData: Button
    private lateinit var btnTest: Button
    private lateinit var rvContent: RecyclerView

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun init(view: View) {
        btnRoll = view.findViewById(R.id.btn_roll)
        btnDataBind = view.findViewById(R.id.btn_data_bind)
        btnTest = view.findViewById(R.id.btn_test)
        btnMaterial = view.findViewById(R.id.btn_material)
        btnStorage = view.findViewById(R.id.btn_storage)
        btnLiveData = view.findViewById(R.id.btn_live_data)
        rvContent = view.findViewById(R.id.rv_content)
        rvContent.layoutManager = LinearLayoutManager(activity)
        rvContent.adapter =
            activity?.let {
                RecyclerCommonAdapter(it, R.layout.item_recycler_linear, LifeItem.default) {
                    view, item ->
                    val tv_title = view.findViewById<TextView>(R.id.tv_title)
                    tv_title.text = item.title
                }
            }

        btnRoll.setOnClickListener {
            startActivity(Intent(activity, RollActivity::class.java))
        }
        btnDataBind.setOnClickListener {
            startActivity(Intent(activity, DataBindActivity::class.java))
        }
        btnTest.setOnClickListener {
            startActivity(Intent(activity, TestActivity::class.java))
        }
        btnMaterial.setOnClickListener {
            activity?.startActivity<MaterialViewActivity>()
        }
        btnLiveData.setOnClickListener {
            startActivity(Intent(activity, LiveDataActivity::class.java))
        }
        btnStorage.setOnClickListener {
            startActivity(activity!!.intentFor<StorageActivity>())
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}