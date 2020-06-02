package com.example.firstkotlin.fragment

import android.view.View
import com.example.firstkotlin.R
import com.example.firstkotlin.base.BaseFragment

/**
 * Created on 2020/6/2.
 *
 */
class MallFragment: BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_mall

    override fun init(view: View) {

    }

    companion object MallFragmentCp {
        fun newInstance(): MallFragment {
            return MallFragment()
        }
    }
}