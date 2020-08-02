package com.tts.currency.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tts.currency.R
import com.tts.currency.api.ApiInterface
import com.tts.currency.api.RetrofitClient
import com.tts.currency.databinding.FragmentHomeBinding
import com.tts.currency.model.Rates
import com.tts.currency.preferences.PreferenceProvider
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {


    lateinit var preferenceProvider: PreferenceProvider
    lateinit var currencyViewModel: CurrencyViewModel
    lateinit var currencyRepository: CurrencyRepository


    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferenceProvider = PreferenceProvider(requireActivity())

        val api: ApiInterface = RetrofitClient(requireActivity()).getClient()
        currencyRepository = CurrencyRepository(api)
        currencyViewModel = getViewModel()
        binding.viewModel = currencyViewModel

        currencyViewModel.onRefresh(txtRefresh)



        val base = preferenceProvider.getBaseCurrency()
        txtBaseCurrencyCode.text = "$base value : 1 $base"

        val existingValue = preferenceProvider.getCurrencyCode()

        var list: List<String>? = existingValue?.toList()

        txtEditCurrencyCode.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_selectBaseFragment)
        }

        txtAddCurrencyCode.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addCurrencyFragment)
        }
        currencyViewModel.stateLiveData.observe(viewLifecycleOwner, Observer { currency ->

            val sb = StringBuilder()

            if (list != null)
                for (i in list.indices) {
                    for (field in Rates::class.java.declaredFields) {
                        if (list[i] == field.name) {
                            for (responseField in currency.rates::class.java.declaredFields) {
                                if (list[i] == responseField.name) {
                                    responseField.isAccessible = true;
                                    val value: Any? = responseField.get(currency.rates)
                                    if (value != null) {
                                        sb.append("${responseField.name} value : $value \n")
                                    }
                                }
                            }
                        }
                    }
                }
            tvCurrencyList.text = sb
        })
    }

    private fun getViewModel(): CurrencyViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CurrencyViewModel(
                    currencyRepository,
                    preferenceProvider
                ) as T
            }
        })[CurrencyViewModel::class.java]

    }
}