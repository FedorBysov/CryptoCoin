package com.example.cryptocoin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoin.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_icon_info.view.*

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
        val tvNameCrypto = itemView.tvNameCrypto
        val iCryptoImage = itemView.iCryptoImage
        val tvPriceCrypto = itemView.tvPriceCrypto
        val tvTimeUpdate = itemView.tvTimeUpdate
    }


    interface OnCoinClickListener{
        fun OnCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}


