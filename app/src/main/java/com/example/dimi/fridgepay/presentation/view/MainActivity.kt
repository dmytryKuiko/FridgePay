package com.example.dimi.fridgepay.presentation.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.dimi.fridgepay.R
import com.example.dimi.fridgepay.presentation.BaseActivity
import com.example.dimi.fridgepay.presentation.BaseFragment
import com.example.dimi.fridgepay.presentation.presenter.ActivityPresenter
import com.example.dimi.fridgepay.utils.ComponentManager
import com.example.dimi.fridgepay.utils.ScreenKeys
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.activity_main_container) as? BaseFragment

    @Inject
    lateinit var presenter: ActivityPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: SupportFragmentNavigator = object :
        SupportFragmentNavigator(this.supportFragmentManager, R.id.activity_main_container) {

        override fun exit() {
            this@MainActivity.finish()
        }

        override fun showSystemMessage(message: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? =
            when (screenKey) {
                ScreenKeys.MAIN_SCREEN -> MainFragment()
                ScreenKeys.BASKET_SCREEN -> BasketFragment()
                else -> null
            }
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun injectModule() {
        ComponentManager.getMainComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            presenter.startNavigation()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed()
    }
}
