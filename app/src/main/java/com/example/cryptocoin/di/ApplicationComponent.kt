package com.example.cryptocoin.di

import android.app.Activity
import android.app.Application
import com.example.cryptocoin.presentation.CoinDetailSInfoFragment
import com.example.cryptocoin.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: CoinDetailSInfoFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}