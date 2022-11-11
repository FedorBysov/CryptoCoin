package com.example.cryptocoin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptocoin.data.dataBase.CoinInfoDbModel
import com.example.cryptocoin.databinding.ItemIconInfoBinding
import com.example.cryptocoin.domain.CoinInfo
import com.squareup.picasso.Picasso

class Adapters : ListAdapter<CoinInfo, CoinItemViewHolder>(CoinDeffCallBack()) {

    var onCoinClickListener: OnCoinClickListener? = null

    //создаем
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        val binding =
            ItemIconInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            with(coin) {

                tvNameCrypto.text = fromsymbol + "/" + tosymbol
                tvPriceCrypto.text = price.toString()
                tvTimeUpdate.text = String.format("Время последнего обновления %s", lastupdate)
                Picasso.get().load(coin.imageurl).into(iCryptoImage)

                root.setOnClickListener {
                    onCoinClickListener?.OnCoinClick(this)
                }
            }
        }
    }

    //колличесвто эл-ов


    interface OnCoinClickListener {
        fun OnCoinClick(coinPriceInfo: CoinInfo)
    }
}


