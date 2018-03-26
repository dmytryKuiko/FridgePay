package com.example.dimi.fridgepay.di.components

import com.example.dimi.fridgepay.presentation.view.MainActivity
import com.example.dimi.fridgepay.di.modules.MainModule
import com.example.dimi.fridgepay.di.scopes.ActivityScope
import com.example.dimi.fridgepay.presentation.view.BasketFragment
import com.example.dimi.fridgepay.presentation.view.MainFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent : BaseComponent<MainActivity> {

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }

    fun inject(fragment: MainFragment)

    fun inject(fragment: BasketFragment)
}