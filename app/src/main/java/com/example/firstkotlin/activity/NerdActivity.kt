package com.example.firstkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstkotlin.R
import com.example.firstkotlin.fragment.PhotoGalleryFragment

/**
 * 《Android编程权威指南》练习
 */
class NerdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nerd)

        if (savedInstanceState == null) {
            val fragment = PhotoGalleryFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}