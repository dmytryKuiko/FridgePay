package com.example.dimi.fridgepay.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.fridgepay.R
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_basket_product_displayable.view.*

class BasketDisplayableAdapter : AdapterDelegate<MutableList<ProductDisplayable>>() {
    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
        LayoutInflater.from(parent?.context)
            .inflate(R.layout.row_basket_product_displayable, parent, false).let {
                BasketViewHolder(it)
            }

    override fun isForViewType(items: MutableList<ProductDisplayable>, position: Int): Boolean =
        items[position] is ProductDisplayable.Product


    override fun onBindViewHolder(
        items: MutableList<ProductDisplayable>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as? ProductDisplayable.Product
                ?: throw ClassCastException("ProductDisplayable is not a Product")
        val contentDisplayableHolder =
            holder as? BasketViewHolder ?: throw ClassCastException("not a ProductHolder")
        contentDisplayableHolder.bind(item, position)
    }

    inner class BasketViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var product: ProductDisplayable.Product

        fun bind(item: ProductDisplayable.Product, position: Int) {
            product = item

            itemView.run {
                val name = "$position.  ${item.name}"
                val price = "${item.price}€"
                row_basket_displayable_name_text_view.text = name
                row_basket_displayable_price_text_view.text = price
            }
        }
    }
}