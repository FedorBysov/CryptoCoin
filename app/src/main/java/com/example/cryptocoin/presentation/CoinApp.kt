package com.example.cryptocoin.presentation

import android.app.Application
import com.example.cryptocoin.di.DaggerApplicationComponent

class CoinApp:Application() {

    val component by lazy{
         DaggerApplicationComponent.factory().create(this)
    }
}