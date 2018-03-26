package com.example.dimi.fridgepay.presentation.view


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle

import com.example.dimi.fridgepay.R
import com.example.dimi.fridgepay.presentation.BaseFragment
import com.example.dimi.fridgepay.presentation.presenter.BasketPresenter
import com.example.dimi.fridgepay.utils.ComponentManager
import com.example.dimi.fridgepay.utils.visible
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class BasketFragment : BaseFragment() {

    @Inject
    lateinit var presenter: BasketPresenter

    override val layoutId: Int
        get() = R.layout.fragment_basket

    override fun injectModule(context: Context) {
        ComponentManager.getMainComponent().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.getToolbarData().observe(this, Observer {
            it?.let {
                toolbar_basket_button.visible(it.basketVisibility)
                toolbar_back_button.visible(it.backVisibility)
                toolbar_basket_count.visible(it.countBasketVisibility)
            }
        })
        toolbar_back_button.setOnClickListener { presenter.backClicked() }
    }

    override fun onBackPressed() {
        presenter.backClicked()
    }
}
