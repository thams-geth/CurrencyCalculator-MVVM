package com.tts.currency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tts.currency.preferences.PreferenceProvider
import kotlinx.android.synthetic.main.fragment_select_base.*

class SelectBaseFragment : Fragment() {


    lateinit var preferenceProvider: PreferenceProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_base, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferenceProvider = PreferenceProvider(requireActivity())

        btnNext.setOnClickListener {

            if (extCurrencyCode.text.toString().isNotEmpty()) {
                preferenceProvider.saveBaseCurrency(extCurrencyCode.text.toString())
                findNavController().navigate(R.id.action_selectBaseFragment_to_addCurrencyFragment)
            } else {
                Toast.makeText(requireContext(), "Please Enter Currency Code", Toast.LENGTH_SHORT)
                    .show()

            }

        }

    }

}