package com.example.cryptocoin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptocoin.R
import com.example.cryptocoin.data.dataBase.CoinInfoDbModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_icon_info.view.*

class Adapters : ListAdapter<CoinInfoDbModel, CoinItemViewHolder>(CoinDeffCallBack()) {

//    //хранит coinInfo
//    var coinInfoList:List<CoinPriceInfo> = listOf ()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    var onCoinClickListener: OnCoinClickListener? =null

    //создаем
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_icon_info, parent, false)
        return CoinItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        val coin = [position]
        with(holder) {
            tvNameCrypto.text = bind.fromsymbol + "/" + coin.tosymbol
            tvPriceCrypto.text = coin.price.toString()
            tvTimeUpdate.text = "Время последнего обновления " + coin.getFormattedDay()
            Picasso.get().load(coin.getFullImageUrl()).into(iCryptoImage)


            itemView.setOnClickListener {
                onCoinClickListener?.OnCoinClick(coin)
            }
        }
    }

    //колличесвто эл-ов
    override fun getItemCount(): Int {
        return coinInfoList.size
    }




    interface OnCoinClickListener{
        fun OnCoinClick(coinPriceInfo: CoinInfoDbModel)
    }
}


