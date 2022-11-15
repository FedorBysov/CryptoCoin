package com.example.cryptocoin.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cryptocoin.data.CoinRepositoryIMPL
import com.example.cryptocoin.data.dataBase.CoinInfoDbModel
import com.example.cryptocoin.domain.GetCoinInfoListUseCase
import com.example.cryptocoin.domain.GetCoinInfoUseCase
import com.example.cryptocoin.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryIMPL(application)

    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val  loadDataUseCase = LoadDataUseCase(repository)



    val coinInfoList = getCoinInfoListUseCase()


    fun getDetailsCoinInfo(fsym: String) = getCoinInfoUseCase(fsym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

}
