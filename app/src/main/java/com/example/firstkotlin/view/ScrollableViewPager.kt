package com.example.firstkotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created on 2020/6/2.
 * ref: https://www.cnblogs.com/zhujiabin/p/7471665.html
 */
class ScrollableViewPager(context: Context, attributeSet: AttributeSet): ViewPager(context, attributeSet) {
    var scrollable: Boolean = true

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (scrollable) {
            super.onInterceptTouchEvent(ev)
        } else {
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (scrollable) {
            super.onTouchEvent(ev)
        } else {
            false
        }
    }
}