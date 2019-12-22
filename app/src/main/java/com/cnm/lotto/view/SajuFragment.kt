package com.cnm.lotto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.cnm.lotto.DateBase
import com.cnm.lotto.MainActivity
import com.cnm.lotto.R
import com.cnm.lotto.network.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_saju.*


class SajuFragment : Fragment(), DateBase {
    private val disposable = CompositeDisposable()
    private var animal = arrayOfNulls<String>(12)
    private val spinnerItems =  resources.getStringArray(R.array.animal_name)
    private val spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,spinnerItems)

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
                        (activity as? MainActivity)?.sajuSummary(it.summary)
                    }
            )
        }
    }

    fun sajuSummary(summary: String) {
        tv_summary.text = summary
    }
}