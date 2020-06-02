package com.example.firstkotlin.fragment

import android.view.View
import com.example.firstkotlin.R
import com.example.firstkotlin.base.BaseFragment

/**
 * Created on 2020/6/2.
 *
 */
class PersonFragment: BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_person

    override fun init(view: View) {
    }

    companion object {
        fun newInstance(): PersonFragment {
            return PersonFragment()
        }
    }
}