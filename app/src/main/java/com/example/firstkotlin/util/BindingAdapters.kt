package com.example.firstkotlin.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * Created on 2020/6/8.
 *
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter(value = ["imageUrl"], requireAll = false)
    fun loadImage(view: ImageView, url: Int?) {
        view.setImageResource(url!!)
    }

    @JvmStatic
    @BindingAdapter("android:paddingLeft")
    fun setPaddingLeft(view: View, padding: Int) {
        view.setPadding(padding,
            view.getPaddingTop(),
            view.getPaddingRight(),
            view.getPaddingBottom())
    }
}