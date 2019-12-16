package com.cnm.lotto

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.cnm.lotto.network.NetworkHelper
import com.cnm.lotto.network.saju.SajuResponse
import com.cnm.lotto.view.FavoriteFragment
import com.cnm.lotto.view.RandomFragment
import com.cnm.lotto.view.ResultFragment
import com.cnm.lotto.view.SajuFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), DateBase {

    private var animal = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            itemSelected(RandomFragment())
            vp_saju.adapter = FragmentAdapter(supportFragmentManager)

            vp_saju.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {}
            })
            if (animal == 13) {
                animal = 1
            }
            NetworkHelper.sajuApi.getSaju(getYear(), getMonth(), getDay(), 11).enqueue(
                object : Callback<SajuResponse> {
                    override fun onFailure(call: Call<SajuResponse>, t: Throwable) {
                        Log.e("saju", "$t")
                    }

                    override fun onResponse(
                        call: Call<SajuResponse>,
                        response: Response<SajuResponse>
                    ) {
                        val body = response.body()
                        if (body != null) {
                            val sajuFragment = SajuFragment()
                            val bundle = Bundle()
                            bundle.putString("summary", body.summary)
                            sajuFragment.arguments = bundle
                            vp_saju.currentItem = 0
                            animal++
                        }
                    }

                }
            )
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
