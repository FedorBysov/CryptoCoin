package com.example.cryptocoin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptocoin.databinding.ActivityMainBinding
import com.example.cryptocoin.pojo.CoinPriceInfo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = Adapters()
        binding.rvTCCryptoInfo.adapter = adapter
 
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]

        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })
        adapter.onCoinClickListener = object :Adapters.OnCoinClickListener{
            override fun OnCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailSInfo.newIntent(this@MainActivity, coinPriceInfo.fromsymbol)
                startActivity(intent)
            }
        }

//        viewModel.getDetailsCoinInfo("BTC").observe(this, Observer {
//            Log.d("TEST_OF_LOADING_DATA", "Succes in activity $it")
//        })

    }























}