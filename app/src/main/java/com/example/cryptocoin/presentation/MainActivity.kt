package com.example.cryptocoin.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoin.databinding.ActivityMainBinding
import com.example.cryptocoin.domain.CoinInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = Adapters()
        binding.rvTCCryptoInfo.adapter = adapter
        binding.rvTCCryptoInfo.itemAnimator = null
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }

        adapter.onCoinClickListener = object : Adapters.OnCoinClickListener {
            override fun OnCoinClick(coinPriceInfo: CoinInfo) {
                val intent = CoinDetailSInfo.newIntent(this@MainActivity, coinPriceInfo.fromsymbol)
                startActivity(intent)
            }
        }


    }


























}