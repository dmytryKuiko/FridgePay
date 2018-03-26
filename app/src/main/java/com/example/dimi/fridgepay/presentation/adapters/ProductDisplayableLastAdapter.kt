package com.example.dimi.fridgepay.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.fridgepay.R
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_product_displayable_product.view.*

class ProductDisplayableLastAdapter : AdapterDelegate<MutableList<ProductDisplayable>>() {
    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
        LayoutInflater.from(parent?.context)
            .inflate(R.layout.row_product_displayable_last, parent, false).let {
                ProductViewHolder(it)
            }

    override fun isForViewType(items: MutableList<ProductDisplayable>, position: Int): Boolean =
        items[position] === ProductDisplayable.Last


    override fun onBindViewHolder(
        items: MutableList<ProductDisplayable>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {}

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view)
}