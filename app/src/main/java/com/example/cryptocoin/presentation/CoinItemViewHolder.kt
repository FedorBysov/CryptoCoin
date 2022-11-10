package com.example.cryptocoin.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_icon_info.view.*

class CoinItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNameCrypto = itemView.tvNameCrypto
        val iCryptoImage = itemView.iCryptoImage
        val tvPriceCrypto = itemView.tvPriceCrypto
        val tvTimeUpdate = itemView.tvTimeUpdate
    }
