package com.example.cryptocoin.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cryptocoin.data.api.ApiFactory
import com.example.cryptocoin.data.api.ApiService
import com.example.cryptocoin.data.dataBase.AppDatabase
import com.example.cryptocoin.data.dataBase.CoinInfoDao
import com.example.cryptocoin.data.mapper.CoinMapper
import com.example.cryptocoin.domain.CoinInfo
import com.example.cryptocoin.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryIMPL(application: Application) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiService

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            val topCoins = apiService.getTopCoinInfo(limit = 15)
            val fromSymbols = mapper.mapNamesListToString(topCoins)
            val jsonContainer = apiService.getFullPriceList(fsyms = fromSymbols)
            val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)

            val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
            coinInfoDao.insertPriceList(dbModelList)
            delay(10000)
        }
    }
}