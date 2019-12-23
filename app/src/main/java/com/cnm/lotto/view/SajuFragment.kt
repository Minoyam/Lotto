package com.cnm.lotto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.cnm.lotto.AnimalItem
import com.cnm.lotto.DateBase
import com.cnm.lotto.R
import com.cnm.lotto.network.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_saju.*


class SajuFragment : Fragment(), DateBase {
    private val disposable = CompositeDisposable()
    private var animal = arrayOfNulls<String>(12)
    private val animalImage = arrayOf<Int>()
    private val animalItem = AnimalItem.values().toList().let {
        for (i in 0 until it.size)
            animalImage[i] = (it[i].ImgResId)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saju, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sajuCreate()
        val spinnerItems = resources.getStringArray(R.array.animal_name)
        sp_select_animal.adapter =
            ArrayAdapter(activity!!, R.layout.spinner_row, spinnerItems, animalImage)
        sp_select_animal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        tv_summary.text = animal[0]
                        iv_animal.setImageResource(R.drawable.saju_mouse)
                    }
                    1 -> {
                        tv_summary.text = animal[1]
                    }
                    2 -> {
                        tv_summary.text = animal[2]
                    }
                    3 -> {
                        tv_summary.text = animal[3]
                    }
                    4 -> {
                        tv_summary.text = animal[4]
                    }
                    5 -> {
                        tv_summary.text = animal[5]
                    }
                    6 -> {
                        tv_summary.text = animal[6]
                    }
                    7 -> {
                        tv_summary.text = animal[7]
                    }
                    8 -> {
                        tv_summary.text = animal[8]
                    }
                    9 -> {
                        tv_summary.text = animal[9]
                    }
                    10 -> {
                        tv_summary.text = animal[10]
                    }
                    11 -> {
                        tv_summary.text = animal[11]
                    }
                }
            }

        }


    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun sajuCreate() {
        for (animal_index in 1..12) {
            disposable.add(
                NetworkHelper.sajuApi.getSaju(
                    getYear(),
                    getMonth(),
                    getDay(),
                    animal_index
                ).observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        animal[animal_index - 1] = it.summary
                    }
            )
        }
    }

}