package com.example.firstkotlin.fragment

import android.util.SparseArray
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.firstkotlin.R
import com.example.firstkotlin.base.BaseFragment
import com.google.android.material.tabs.TabLayout

/**
 * Created on 2020/6/2.
 *
 */
class MallFragment: BaseFragment() {
    private val titleList = mutableListOf("体育", "电影", "小说", "电视剧", "动漫", "军事", "时政", "社会")
    private lateinit var tbMall: Toolbar
    private lateinit var tlMall: TabLayout
    private lateinit var vpMall: ViewPager2
    private lateinit var ivSearch: ImageView
    private var fragmentList: SparseArray<Fragment> = SparseArray(titleList.size)

    override fun getLayoutId(): Int = R.layout.fragment_mall

    override fun init(view: View) {
        tbMall = view.findViewById(R.id.tb_mall)
        tlMall = view.findViewById(R.id.tl_mall)
        vpMall = view.findViewById(R.id.vp_mall)
        ivSearch = view.findViewById(R.id.iv_search)

        // 必须是AppCompatActivity才有这个方法
        (activity as AppCompatActivity).setSupportActionBar(tbMall)
        // Toolbar上的文字和按钮全是Activity传过来的, 这是因为只有Activity的onCreateOptionsMenu()被调用了, 但是Fragment的并没有被调用
        setHasOptionsMenu(true)
        initFragment()
        initTabLayout()
        initViewPager()
    }

    private fun initFragment() {
        fragmentList.clear()
        var index = 0
        titleList.forEach {
            fragmentList.put(index++, GoodsListFragment.newInstance(it))
        }
    }

    private fun initTabLayout() {
        titleList.forEach{
            tlMall.addTab(tlMall.newTab().setText(it))
        }
        tlMall.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                vpMall.currentItem = tab!!.position
            }
        })
    }

    private fun initViewPager() {
        vpMall.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragmentList.size()

            override fun createFragment(position: Int): Fragment = fragmentList.get(position)
        }
        vpMall.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tlMall.getTabAt(position)!!.select()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // 先clear()一下, 这样按钮就只有Fragment中设置的自己的了, 不会有Activity中的按钮
//        menu.clear()
//        inflater.inflate(R.menu.bottom_nav_menu, menu)
    }

    companion object {
        fun newInstance(): MallFragment {
            return MallFragment()
        }
    }
}