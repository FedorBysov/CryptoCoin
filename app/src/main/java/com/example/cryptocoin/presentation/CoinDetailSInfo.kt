package com.example.cryptocoin.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoin.databinding.ActivityCoinDetailSinfoBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail_sinfo.*


class CoinDetailSInfo : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: ActivityCoinDetailSinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailSinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        viewModel.getDetailsCoinInfo(fromSymbol).observe(this, Observer {
            binding.tvFromSymbol.text = it.tosymbol
            binding.tvToSymbol.text = it.fromsymbol
            binding.tvPrice.text = it.price.toString()
            binding.tvMinPrice.text = it.lowday.toString()
            binding.tvMaxPrice.text = it.highday.toString()
            binding.tvLastMarket.text = it.lastmarket
            binding.tvLastUpdate.text = it.lastupdate
            Picasso.get().load( it.imageurl).into(ivLogoCoin)
        })


    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fsym"

        private const val EMPTY_SYMBOL =""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailSInfo::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}