package com.example.dimi.fridgepay.di.components

import com.example.dimi.fridgepay.App
import com.example.dimi.fridgepay.di.modules.AppModule
import com.example.dimi.fridgepay.di.modules.NavigationModule
import com.example.dimi.fridgepay.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, NavigationModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        @Singleton
        fun application(app: App): Builder

        @BindsInstance
        @Singleton
        fun url(url: String): Builder

        fun build(): AppComponent
    }

    fun mainComponentBuilder(): MainComponent.Builder
}