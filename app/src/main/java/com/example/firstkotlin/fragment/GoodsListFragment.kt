package com.example.firstkotlin.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.firstkotlin.R
import com.example.firstkotlin.base.BaseFragment

/**
 * Created on 2020/6/2.
 *
 */
class GoodsListFragment: BaseFragment() {
    private lateinit var tvTitle: TextView

    override fun getLayoutId(): Int = R.layout.fragment_goods_list

    override fun init(view: View) {
        var type = if (arguments != null) {
            arguments?.getString("goods_type")
        } else {
            ""
        }
        tvTitle = view.findViewById(R.id.tv_title)
        tvTitle.text = type
    }

    companion object {
        fun newInstance(type: String): Fragment {
            val instance = GoodsListFragment()
            val bundle = Bundle()
            bundle.putString("goods_type", type)
            instance.arguments = bundle
            return instance
        }
    }
}