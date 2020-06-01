package com.example.firstkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var btnRoll: Button
    private lateinit var btnDataBind: Button
    private lateinit var btnTest: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
         * 视图绑定 ref：https://developer.android.com/topic/libraries/view-binding
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnRoll = binding.btnRoll
        btnDataBind = binding.btnDataBind
        btnTest = binding.btnTest

        btnRoll.setOnClickListener {
            startActivity(Intent(this@MainActivity, RollActivity::class.java))
        }
        btnDataBind.setOnClickListener {
            startActivity(Intent(this@MainActivity, DataBindActivity::class.java))
        }
        btnTest.setOnClickListener {
            startActivity(Intent(this@MainActivity, TestActivity::class.java))
        }
        btn_recycler.setOnClickListener {
            startActivity<RecyclerViewActivity>()
        }
    }

}
