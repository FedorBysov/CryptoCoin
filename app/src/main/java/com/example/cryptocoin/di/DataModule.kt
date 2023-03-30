package com.example.cryptocoin.di

import android.app.Application
import com.example.cryptocoin.data.CoinRepositoryIMPL
import com.example.cryptocoin.data.dataBase.AppDatabase
import com.example.cryptocoin.data.dataBase.CoinInfoDao
import com.example.cryptocoin.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryIMPL):CoinRepository

    companion object{
        @Provides
        fun provideCoinInfoDao(application: Application):CoinInfoDao{
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}