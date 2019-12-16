package com.cnm.lotto.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cnm.lotto.R
import kotlinx.android.synthetic.main.fragment_saju.*


class SajuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            val args = arguments
            tv_test.text = args!!.getString("summary")
            Log.e("summary","${args.getString("summary")}")

        return inflater.inflate(R.layout.fragment_saju, container, false)
    }

}