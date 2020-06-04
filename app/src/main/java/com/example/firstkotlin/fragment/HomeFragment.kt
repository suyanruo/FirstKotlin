package com.example.firstkotlin.fragment

import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.firstkotlin.R
import com.example.firstkotlin.activity.*
import com.example.firstkotlin.base.BaseFragment
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
    private lateinit var btnTest: Button

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun init(view: View) {
        btnRoll = view.findViewById(R.id.btn_roll)
        btnDataBind = view.findViewById(R.id.btn_data_bind)
        btnTest = view.findViewById(R.id.btn_test)
        btnMaterial = view.findViewById(R.id.btn_material)
        btnStorage = view.findViewById(R.id.btn_storage)

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
        btnStorage.setOnClickListener {
            startActivity(activity!!.intentFor<StorageActivity>().clearTop())
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}