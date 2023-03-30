package com.example.cryptocoin.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocoin.databinding.FragmentCoinDetailSinfoBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail_sinfo.*
import javax.inject.Inject


class CoinDetailSInfoFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentCoinDetailSinfoBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityCoinDetailSinfoBinding is null")

    private val component by lazy {
        (requireActivity().application as CoinApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinDetailSinfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]

        val fromSymbol =getSymbol()

        viewModel.getDetailsCoinInfo(fromSymbol).observe(viewLifecycleOwner, Observer {
            binding.tvFromSymbol.text = it.tosymbol
            binding.tvToSymbol.text = it.fromsymbol
            binding.tvPrice.text = it.price.toString()
            binding.tvMinPrice.text = it.lowday.toString()
            binding.tvMaxPrice.text = it.highday.toString()
            binding.tvLastMarket.text = it.lastmarket
            binding.tvLastUpdate.text = it.lastupdate
            Picasso.get().load(it.imageurl).into(binding.ivLogoCoin)
        })

    }




    private fun getSymbol(): String {
        return requireArguments().getString(EXTRA_FROM_SYMBOL, EMPTY_SYMBOL)
    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fsym"

        private const val EMPTY_SYMBOL = ""

        fun newIstance(fromSymbol: String): Fragment {
            return CoinDetailSInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromSymbol)
                }
            }
        }
    }
}