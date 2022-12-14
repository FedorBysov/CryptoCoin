package com.example.cryptocoin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptocoin.databinding.ActivityCoinDetailSinfoBinding
import com.squareup.picasso.Picasso



class CoinDetailSInfo : AppCompatActivity() {

    private lateinit var binding: ActivityCoinDetailSinfoBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailSinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        if (fromSymbol != null) {
            viewModel.getDetailsCoinInfo(fromSymbol).observe(this, Observer {
                Log.d("TEST_LOADING_CRYPTO_INFO", it.toString())
                binding.toSlash.text = it.tosymbol
//                tvPriceNow.text = it.price.toString()
//                toSlash.text = it.tosymbol
//                fromSlash.text = it.fromsymbol
//                tvMinimumPriceNow.text = it.lowday.toString()
//                tvMaxPriceNow.text = it.highday.toString()
//                tvLastDealNow.text = it.lasttradeid
//                tvTimeUpdate.text = "Последняя сделка : " + it.getFormattedDay()
                Picasso.get().load(it.getFullImageUrl()).into(binding.iCryptoImage)
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