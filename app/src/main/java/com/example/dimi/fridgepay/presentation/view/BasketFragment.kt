package com.example.dimi.fridgepay.presentation.view


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.example.dimi.fridgepay.R
import com.example.dimi.fridgepay.presentation.BaseFragment
import com.example.dimi.fridgepay.presentation.adapters.basket.ProductsBasketAdapter
import com.example.dimi.fridgepay.presentation.presenter.basket.BasketPresenter
import com.example.dimi.fridgepay.utils.ComponentManager
import com.example.dimi.fridgepay.utils.visible
import kotlinx.android.synthetic.main.fragment_basket.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class BasketFragment : BaseFragment() {

    @Inject
    lateinit var presenter: BasketPresenter


    private val productsAdapter by lazy { ProductsBasketAdapter() }

    override val layoutId: Int
        get() = R.layout.fragment_basket

    override fun injectModule(context: Context) {
        ComponentManager.getMainComponent().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        fragment_basket_recycler_view.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = productsAdapter
            setHasFixedSize(true)
        }

        presenter.run {
            getToolbarData().observe(this@BasketFragment, Observer {
                it?.let {
                    toolbar_basket_button.visible(it.basketVisibility)
                    toolbar_back_button.visible(it.backVisibility)
                    toolbar_basket_count.visible(it.countBasketVisibility)
                }
            })

            getData().observe(this@BasketFragment, Observer {
                it?.let { productsAdapter.setNewData(it)}
            })

            getBasketPrice().observe(this@BasketFragment, Observer {
                it?.let {
                    fragment_basket_sum_text_view.text = it
                }
            })
        }
        toolbar_back_button.setOnClickListener { presenter.backClicked() }
    }

    override fun onBackPressed() {
        presenter.backClicked()
    }
}
