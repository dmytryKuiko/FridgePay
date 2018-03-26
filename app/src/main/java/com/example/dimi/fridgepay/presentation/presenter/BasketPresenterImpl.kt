package com.example.dimi.fridgepay.presentation.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.dimi.fridgepay.model.ToolbarModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class BasketPresenterImpl
@Inject constructor(
    private val router: Router
) : BasketPresenter {

    private val toolbarLiveData: MutableLiveData<ToolbarModel> = MutableLiveData()

    init {
        toolbarLiveData.value =
                ToolbarModel(backVisibility = true, basketVisibility = false, countBasketVisibility = false)
    }

    override fun getToolbarData(): LiveData<ToolbarModel> = toolbarLiveData

    override fun backClicked() {
        router.exit()
    }
}