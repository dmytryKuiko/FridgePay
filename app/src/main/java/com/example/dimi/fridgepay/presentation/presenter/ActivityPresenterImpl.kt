package com.example.dimi.fridgepay.presentation.presenter

import com.example.dimi.fridgepay.utils.ScreenKeys
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ActivityPresenterImpl
@Inject constructor(private val router: Router) : ActivityPresenter {

    override fun startNavigation() {
        router.newRootScreen(ScreenKeys.MAIN_SCREEN)
    }
}