package com.cnm.lotto

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cnm.lotto.view.FavoriteFragment
import com.cnm.lotto.view.RandomFragment
import com.cnm.lotto.view.ResultFragment
import com.cnm.lotto.view.SajuFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            itemSelected(RandomFragment())
        }

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_random -> {
                    itemSelected(RandomFragment())
                }
                R.id.action_result -> {
                    itemSelected(ResultFragment())
                }
                R.id.action_favorite -> {
                    itemSelected(FavoriteFragment())
                }
                else -> {
                    false
                }
            }

        }
    }

    private fun itemSelected(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_main, fragment, fragment.javaClass.simpleName)
            .commit()
        return true
    }

}
