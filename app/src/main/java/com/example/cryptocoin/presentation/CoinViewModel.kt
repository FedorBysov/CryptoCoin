package com.example.cryptocoin.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptocoin.data.api.ApiFactory
import com.example.cryptocoin.data.dataBase.AppDatabase
import com.example.cryptocoin.data.dataBase.CoinInfoDbModel
import com.example.cryptocoin.data.api.model.CoinPriceInfoJsonContainerDto
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()


    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailsCoinInfo(fsym: String): LiveData<CoinInfoDbModel> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fsym)
    }


    //загружаем данные из сети
     private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinInfo(limit = 10)
            .map { it.data?.map { it.coinName?.name }?.joinToString(",")  }
            .flatMap { it?.let { it1 -> ApiFactory.apiService.getFullPriceList(fsyms = it1) } }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
             if (it != null) {
                 db.coinPriceInfoDao().insertPriceList(it)
             }
                Log.d("TEST_OF_LOADING_DATA", it.toString())
            }, {
                Log.d("TEST_OF_LOADING_DATA", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }


//    private fun getPriceListFromRawData(coinPriceInfoJsonContainerDto: CoinPriceInfoJsonContainerDto): List<CoinInfoDbModel>? {
//        val result = ArrayList<CoinInfoDbModel>()
//        val jsonObject = coinPriceInfoJsonContainerDto.CoinPriceInfoJsonObject ?: return null
//        val coinKeySet = jsonObject.keySet()
//        for (coinKey in coinKeySet) {
//            val currencyJson = jsonObject.getAsJsonObject(coinKey)
//            val currencyKeySet = currencyJson.keySet()
//            for (coinKeyAbout in currencyKeySet) {
//                val priceInfo = Gson().fromJson(
//                    currencyJson.getAsJsonObject(coinKeyAbout),
//                    CoinInfoDbModel::class.java
//                )
//                result.add(priceInfo)
//            }
//        }
//        return result
//    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
