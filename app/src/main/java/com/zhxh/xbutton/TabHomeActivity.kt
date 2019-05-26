package com.zhxh.xbutton

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import com.zhxh.xbutton.dummy.ChartData

import java.util.ArrayList

class TabHomeActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * [FragmentPagerAdapter] derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private var mViewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null


    private val titleList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        tabLayout = findViewById(R.id.tabLayout)
        mViewPager = findViewById(R.id.mViewPager)

        for (i in 0..4) {
            titleList.add("收益榜$i")
        }

        for (i in titleList.indices) {
            //从源码上看这个不起作用 起作用的是getPageTitle
            tabLayout!!.addTab(tabLayout!!.newTab().setText("tab$i"))
        }

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        mViewPager!!.adapter = mSectionsPagerAdapter
        mViewPager!!.currentItem = 0

        tabLayout!!.setupWithViewPager(mViewPager)

    }

    override fun onListFragmentInteraction(item: ChartData) {

    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

        override fun getItem(position: Int): Fragment {
            return ItemFragment.newInstance(position % 2 + 1)
        }

        override fun getCount(): Int {
            return titleList.size
        }
    }
}
