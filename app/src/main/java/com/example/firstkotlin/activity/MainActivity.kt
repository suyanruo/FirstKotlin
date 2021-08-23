package com.example.firstkotlin.activity

import android.os.Bundle
import android.util.SparseArray
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ActivityMainBinding
import com.example.firstkotlin.fragment.HomeFragment
import com.example.firstkotlin.fragment.MallFragment
import com.example.firstkotlin.fragment.PersonFragment

/**
 * @author zhangjian
 * on 2020-6-2
 *
 * ViewPager + Fragment : https://juejin.im/post/5de4e4356fb9a071b77c896a
 */
class MainActivity : AppCompatActivity() {
    private val HOME_FRAGMENT_INDEX = 0
    private val MALL_FRAGMENT_INDEX = 1
    private val PERSON_FRAGMENT_INDEX = 2
    private lateinit var binding: ActivityMainBinding
    private var fragmentList: SparseArray<Fragment> = SparseArray(3)
    private var homeFragment: HomeFragment? = null
    private var mallFragment: MallFragment? = null
    private var personFragment: PersonFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        /*
         * 视图绑定 ref：https://developer.android.com/topic/libraries/view-binding
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragment()

        val fragmentAdapter = object : FragmentPagerAdapter(supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment = fragmentList.get(position)

            override fun getCount(): Int = fragmentList.size()
        }
        binding.vpMain.apply {
            scrollable = false
            adapter = fragmentAdapter
            currentItem = 0
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {}

                override fun onPageSelected(position: Int) {
                    binding.vpMain.currentItem = position
                    binding.bnvMain.menu.getItem(position).isChecked = true
                }

            })
        }

        // 去除背景底色
        binding.bnvMain.apply {
            itemIconTintList = null
            setOnNavigationItemSelectedListener {
                    item -> switchMenu(item)
                true
            }
        }

    }

    private fun initFragment() {
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance()
        }
        if (mallFragment == null) {
            mallFragment = MallFragment.newInstance()
        }
        if (personFragment == null) {
            personFragment = PersonFragment.newInstance()
        }

        fragmentList.clear()
        fragmentList.put(HOME_FRAGMENT_INDEX, homeFragment)
        fragmentList.put(MALL_FRAGMENT_INDEX, mallFragment)
        fragmentList.put(PERSON_FRAGMENT_INDEX, personFragment)

    }

    /**
     * 导航栏切换方法
     */
    private fun switchMenu(item: MenuItem) {
        when (item.itemId) {
            R.id.navigation_home -> binding.vpMain.currentItem = 0
            R.id.navigation_mall -> binding.vpMain.currentItem = 1
            else -> binding.vpMain.currentItem = 2
        }
    }
}
