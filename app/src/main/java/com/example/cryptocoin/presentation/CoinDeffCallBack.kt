package com.example.cryptocoin.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocoin.data.dataBase.CoinInfoDbModel

class CoinDeffCallBack : DiffUtil.ItemCallback<CoinInfoDbModel>() {
    override fun areItemsTheSame(oldItem: CoinInfoDbModel, newItem: CoinInfoDbModel): Boolean {
        return oldItem.fromsymbol == newItem.fromsymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoDbModel, newItem: CoinInfoDbModel): Boolean {
        return oldItem == newItem
    }
}