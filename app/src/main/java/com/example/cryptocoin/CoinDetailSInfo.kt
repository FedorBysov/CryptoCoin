package com.example.cryptocoin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail_sinfo.*



class CoinDetailSInfo : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail_sinfo)

        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        if (fromSymbol != null) {
            viewModel.getDetailsCoinInfo(fromSymbol).observe(this, Observer {
                Log.d("TEST_LOADING_CRYPTO_INFO", it.toString())
                toSlash.text = it.tosymbol
//                tvPriceNow.text = it.price.toString()
//                toSlash.text = it.tosymbol
//                fromSlash.text = it.fromsymbol
//                tvMinimumPriceNow.text = it.lowday.toString()
//                tvMaxPriceNow.text = it.highday.toString()
//                tvLastDealNow.text = it.lasttradeid
//                tvTimeUpdate.text = "Последняя сделка : " + it.getFormattedDay()
                Picasso.get().load(it.getFullImageUrl()).into(iCryptoImage)
            })
        }


    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fsym"

        fun newIntent(context: Context, fromSymbol:String):Intent{
            val intent = Intent(context, CoinDetailSInfo::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}