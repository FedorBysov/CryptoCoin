package com.example.cryptocoin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoin.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class Adapters : RecyclerView.Adapter<Adapters.CoinViewHolder>() {

    //хранит coinInfo
    var coinInfoList:List<CoinPriceInfo> = listOf ()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? =null

    //создаем
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_icon_info, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            tvNameCrypto.text = coin.fromsymbol + "/" + coin.tosymbol
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

    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNameCrypto = itemView.findViewById<TextView>(R.id.tvNameCrypto)
        val iCryptoImage = itemView.findViewById<ImageView>(R.id.iCryptoImage)
        val tvPriceCrypto = itemView.findViewById<TextView>(R.id.tvPriceCrypto)
        val tvTimeUpdate = itemView.findViewById<TextView>(R.id.tvTimeUpdate)
    }


    interface OnCoinClickListener{
        fun OnCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}


