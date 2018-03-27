package com.example.dimi.fridgepay.presentation.adapters.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.dimi.fridgepay.R
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.row_product_displayable_product.view.*

class ProductDisplayableProductAdapter(
    private val clickProduct: (ProductDisplayable.Product) -> Unit,
    private val longClickProduct: (ProductDisplayable.Product) -> Unit
) : AdapterDelegate<MutableList<ProductDisplayable>>() {
    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
        LayoutInflater.from(parent?.context)
            .inflate(R.layout.row_product_displayable_product, parent, false).let {
                ProductViewHolder(it, clickProduct, longClickProduct)
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
            holder as? ProductViewHolder
                    ?: throw ClassCastException("not a ProductHolder")
        contentDisplayableHolder.bind(item, position)
    }

    inner class ProductViewHolder(
        view: View,
        clickListener: (ProductDisplayable.Product) -> Unit,
        longClickListener: (ProductDisplayable.Product) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private lateinit var product: ProductDisplayable.Product

        init {
            itemView.run {
                setOnClickListener { clickListener.invoke(product) }
                setOnLongClickListener {
                    longClickListener.invoke(product)
                    true
                }
            }
        }

        fun bind(item: ProductDisplayable.Product, position: Int) {
            product = item


            itemView.run {
                val name = "$position.  ${item.name}"
                val price = "${item.price}€"
                row_product_displayable_name_text_view.text = name
                row_product_displayable_price_text_view.text = price


                val imagePath =
                    if (item.imageName.toLowerCase().contains(Regex(REGEX_PATTERN))) {
                        item.imageName
                    } else {
                        item.imageFallback
                    }
                Glide.with(this)
                    .load(imagePath)
                    .apply(
                        RequestOptions()
                            .centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    ).into(row_product_displayable_image_view)
            }
        }
    }

    companion object {
        const val REGEX_PATTERN = "^.*?(.png|.jpg|.bmp|.jpeg).*$"
    }
}