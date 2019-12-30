package com.cnm.lotto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cnm.lotto.R
import com.cnm.lotto.data.AnimalItem

class CustomSpinnerAdapter(context: Context, var listItemsTxt: Array<String>,var listItemsImg : Array<AnimalItem>) : BaseAdapter() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, converterView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ItemRowHolder
        if (converterView == null) {
            view = mInflater.inflate(R.layout.spinner_row, parent, false)
            viewHolder = ItemRowHolder(view)
            view?.tag = viewHolder
        } else {
            view = converterView
            viewHolder = view.tag as ItemRowHolder
        }
        viewHolder.label.text = listItemsTxt[position]
        viewHolder.imgLabel.setImageResource(listItemsImg[position].ImgResId)
        return view
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listItemsTxt.size
    }

    private class ItemRowHolder(row: View?) {
        val label = row?.findViewById(R.id.tv_spinner_name) as TextView
        val imgLabel = row?.findViewById(R.id.iv_spinner_image) as ImageView
    }
}