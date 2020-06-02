package com.example.firstkotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.firstkotlin.R
import com.example.firstkotlin.activity.DataBindActivity
import com.example.firstkotlin.activity.MaterialViewActivity
import com.example.firstkotlin.activity.RollActivity
import com.example.firstkotlin.activity.TestActivity
import com.example.firstkotlin.base.BaseFragment
import org.jetbrains.anko.startActivity

/**
 * Created on 2020/6/2.
 *
 */
class HomeFragment: BaseFragment() {
    private lateinit var btnRoll: Button
    private lateinit var btnDataBind: Button
    private lateinit var btnMaterial: Button
    private lateinit var btnTest: Button

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun init(view: View) {
        btnRoll = view.findViewById(R.id.btn_roll)
        btnDataBind = view.findViewById(R.id.btn_data_bind)
        btnTest = view.findViewById(R.id.btn_test)
        btnMaterial = view.findViewById(R.id.btn_material)

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
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}