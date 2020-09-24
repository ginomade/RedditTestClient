package com.gino.reddittestclient

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gino.reddittestclient.ui.DetailFragment
import com.gino.reddittestclient.ui.NewsFragment
import com.gino.reddittestclient.utils.RedditNewsItem

class MainActivity : AppCompatActivity(), NewsFragment.ElementSelectedListener {

    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsFragment = NewsFragment()
        newsFragment.setListener(this)
        changeFragment(newsFragment)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            twoPane = true
        }
    }


    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.setCustomAnimations(
            R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit
        )
        ft.replace(R.id.activity_base_content, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun showDetailFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.setCustomAnimations(
            R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit
        )
        ft.replace(R.id.detail_content, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    /**
     * Finish activity when reaching the last fragment.
     */
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    fun showDetail(item: RedditNewsItem) {
        val bundle = Bundle()
        bundle?.putParcelable("SELECTED_ITEM", item)
        val fragment: DetailFragment
        fragment = DetailFragment.newInstance(bundle)

        if (twoPane) {
            showDetailFragment(fragment)
        } else {
            changeFragment(fragment)
        }
    }

    override fun elementSelected(item: RedditNewsItem) {

        showDetail(item)

    }
}