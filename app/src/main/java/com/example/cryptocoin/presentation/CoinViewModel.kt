package com.example.cryptocoin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocoin.data.CoinRepositoryIMPL
import com.example.cryptocoin.domain.GetCoinInfoListUseCase
import com.example.cryptocoin.domain.GetCoinInfoUseCase
import com.example.cryptocoin.domain.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loadDataUseCase: LoadDataUseCase
):ViewModel()  {


        val coinInfoList = getCoinInfoListUseCase()


        fun getDetailsCoinInfo(fsym: String) = getCoinInfoUseCase(fsym)

        init {
            viewModelScope.launch {
                loadDataUseCase()
            }
        }

}
