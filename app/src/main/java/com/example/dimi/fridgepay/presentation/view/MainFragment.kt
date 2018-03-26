package com.example.dimi.fridgepay.presentation.view


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

import com.example.dimi.fridgepay.R
import com.example.dimi.fridgepay.presentation.BaseFragment
import com.example.dimi.fridgepay.presentation.adapters.ProductsMainAdapter
import com.example.dimi.fridgepay.presentation.presenter.MainPresenter
import com.example.dimi.fridgepay.utils.ComponentManager
import com.example.dimi.fridgepay.utils.visible
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainFragment : BaseFragment() {

    @Inject
    lateinit var presenter: MainPresenter

    private val productsAdapter by lazy {
        ProductsMainAdapter(
            clickProduct = presenter::productClicked,
            longClickProduct = presenter::productLongClicked
        )
    }

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun injectModule(context: Context) {
        ComponentManager.getMainComponent().inject(this)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_main_recycler_view.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = productsAdapter
            setHasFixedSize(true)
        }

        presenter.run {

            getUiState().observe(this@MainFragment, Observer {
                it?.let {
                    toolbar_basket_button.visible(it.toolbarModel.basketVisibility)
                    toolbar_back_button.visible(it.toolbarModel.backVisibility)
                    toolbar_basket_count.visible(it.toolbarModel.countBasketVisibility)
                    toolbar_basket_count.text = "${it.viewDynamicStateMain.basketCount}"
                    fragment_main_buy_button.isEnabled = it.viewDynamicStateMain.buyButtonEnabled
                }
            })

            getData().observe(this@MainFragment, Observer {
                it?.let { productsAdapter.setNewData(it) }
                fragment_main_swipe_refresh_layout.isRefreshing = false
            })

            getNotification().observe(this@MainFragment, Observer {
                it?.let {
                    Toast.makeText(this@MainFragment.activity, it, Toast.LENGTH_LONG).show()
                }
            })
        }

        toolbar_basket_button.setOnClickListener { presenter.basketClicked() }
        fragment_main_swipe_refresh_layout.setOnRefreshListener { presenter.swipeRefreshed() }
        presenter.listenBuyButton(RxView.clicks(fragment_main_buy_button))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispodeRxBinding()
    }
}
