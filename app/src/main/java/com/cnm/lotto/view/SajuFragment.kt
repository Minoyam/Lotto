package com.cnm.lotto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.cnm.lotto.DateBase
import com.cnm.lotto.R
import com.cnm.lotto.adapter.CustomSpinnerAdapter
import com.cnm.lotto.data.AnimalItem
import com.cnm.lotto.network.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_saju.*


class SajuFragment : Fragment(), DateBase {
    private val disposable = CompositeDisposable()
    private var animal = arrayOfNulls<String>(12)
    private val spinnerItems = resources.getStringArray(R.array.animal_name)
    private val spinnerImgItems = AnimalItem.values()

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

        sp_select_animal.adapter = CustomSpinnerAdapter(activity!!, spinnerItems, spinnerImgItems)

        sp_select_animal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                sajuSummary(position)
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
    private fun sajuSummary(position: Int) {
        with(tv_summary) {
            when (position) {
                0 -> { this.text = animal[0] }
                1 -> { this.text  = animal[1] }
                2 -> { this.text  = animal[2] }
                3 -> { this.text  = animal[3] }
                4 -> { this.text  = animal[4] }
                5 -> { this.text  = animal[5] }
                6 -> { this.text  = animal[6] }
                7 -> { this.text  = animal[7] }
                8 -> { this.text  = animal[8] }
                9 -> { this.text  = animal[9] }
                10 -> { this.text  = animal[10] }
                11 -> { this.text  = animal[11] }
            }
        }
    }


}