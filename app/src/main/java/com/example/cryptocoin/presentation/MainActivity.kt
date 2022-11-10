package com.example.cryptocoin.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoin.databinding.ActivityMainBinding
import com.example.cryptocoin.data.dataBase.CoinInfoDbModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = Adapters()
        rvTCCryptoInfo.adapter = adapter

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })

        adapter.onCoinClickListener = object : Adapters.OnCoinClickListener {
            override fun OnCoinClick(coinPriceInfo: CoinInfoDbModel) {
                val intent = CoinDetailSInfo.newIntent(this@MainActivity, coinPriceInfo.fromsymbol)
                startActivity(intent)
            }
        }

//        viewModel.getDetailsCoinInfo("BTC").observe(this, Observer {
//            Log.d("TEST_OF_LOADING_DATA", "Succes in activity $it")
//        })
    }

//    Binding
//    architecture
//    mapper-s

























}