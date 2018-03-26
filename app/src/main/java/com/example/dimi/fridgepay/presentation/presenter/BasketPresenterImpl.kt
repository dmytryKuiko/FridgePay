package com.example.dimi.fridgepay.presentation.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.dimi.fridgepay.domain.BasketInteractor
import com.example.dimi.fridgepay.model.ProductDisplayable
import com.example.dimi.fridgepay.model.ToolbarModel
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class BasketPresenterImpl
@Inject constructor(
    private val interactor: BasketInteractor,
    private val router: Router
) : BasketPresenter {

    private val toolbarLiveData: MutableLiveData<ToolbarModel> = MutableLiveData()

    private val productsLiveData: MutableLiveData<List<ProductDisplayable>> = MutableLiveData()

    init {
        toolbarLiveData.value =
                ToolbarModel(
                    backVisibility = true,
                    basketVisibility = false,
                    countBasketVisibility = false
                )
        interactor.getProducts()
            .subscribe((productsLiveData::postValue), (this::handleError))
    }

    override fun getToolbarData(): LiveData<ToolbarModel> = toolbarLiveData

    override fun backClicked() {
        router.exit()
    }

    override fun getData(): LiveData<List<ProductDisplayable>> = productsLiveData

    private fun handleError(throwable: Throwable) {
        Timber.d(throwable)
    }
}