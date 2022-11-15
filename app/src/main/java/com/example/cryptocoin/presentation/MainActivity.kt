package com.example.cryptocoin.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoin.R
import com.example.cryptocoin.databinding.ActivityMainBinding
import com.example.cryptocoin.domain.CoinInfo

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
                if (binding.fragmentContainer == null) {
                    launchDetailActivity(coinPriceInfo.fromsymbol)
                } else {
                    launchDetailFragment(coinPriceInfo.fromsymbol)
                }
            }
        }


    }

    private fun launchDetailActivity(fromSymbol: String) {
        val intent = CoinDetailActivity.newIntent(this@MainActivity, fromSymbol)
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CoinDetailSInfoFragment.newIstance(fromSymbol))
            .addToBackStack(null)
            .commit()

    }


}