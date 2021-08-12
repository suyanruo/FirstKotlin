package com.example.firstkotlin.activity

import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.R
import com.example.firstkotlin.adapter.RecyclerCommonAdapter

/**
 * 列举所有安装的应用
 */
class AppListActivity : AppCompatActivity() {
    private lateinit var rvAppList: RecyclerView
    private lateinit var adapter: RecyclerCommonAdapter<ResolveInfo>
    private lateinit var appList: List<ResolveInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_list)

        rvAppList = findViewById(R.id.rv_app_list)
        rvAppList.layoutManager = LinearLayoutManager(this)
        setupAdapter()
    }

    private fun setupAdapter() {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        appList = packageManager.queryIntentActivities(intent, 0)
        appList.sortedWith(Comparator { o1, o2 ->
            String.CASE_INSENSITIVE_ORDER.compare(
                o1.loadLabel(packageManager).toString(),
                o2.loadLabel(packageManager).toString()
            )
        })

        adapter = RecyclerCommonAdapter(
            this, android.R.layout.simple_list_item_1, appList
        ) { view, resolveInfo ->
            val textView = view as TextView
            textView.text = resolveInfo.loadLabel(packageManager).toString()

            view.setOnClickListener {
                val activityInfo = resolveInfo.activityInfo
                val intent = Intent(Intent.ACTION_MAIN).apply {
                    setClassName(activityInfo.applicationInfo.packageName, activityInfo.name)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                val ct = view.context
                ct.startActivity(intent)
            }
        }
        rvAppList.adapter = adapter
    }
}