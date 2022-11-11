package com.example.cryptocoin.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocoin.data.dataBase.CoinInfoDbModel
import com.example.cryptocoin.domain.CoinInfo

class CoinDeffCallBack : DiffUtil.ItemCallback<CoinInfo>() {


    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromsymbol == newItem.fromsymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}