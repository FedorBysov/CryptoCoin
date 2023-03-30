package com.example.cryptocoin.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(fromSymbol:String): LiveData<CoinInfo>{
        return repository.getCoinInfo(fromSymbol)
    }
}