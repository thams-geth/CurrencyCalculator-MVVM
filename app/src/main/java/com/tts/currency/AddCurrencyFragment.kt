package com.tts.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tts.currency.preferences.PreferenceProvider
import kotlinx.android.synthetic.main.fragment_add_currency.*
import java.util.*

class AddCurrencyFragment : Fragment() {

    lateinit var preferenceProvider: PreferenceProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_currency, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferenceProvider = PreferenceProvider(requireActivity())
        txtBaseCurrency.text = preferenceProvider.getBaseCurrency()


        val set: TreeSet<String> = TreeSet<String>()
        var existingCode = preferenceProvider.getCurrencyCode()
        if (existingCode != null) {
            for (temp in existingCode) {
                set.add(temp)
            }
        }

        ivAddCurrencyCode.setOnClickListener {
            if (extCurrencyCode.text.toString().isNotEmpty()) {
                set.add(extCurrencyCode.text.toString())
                preferenceProvider.saveCurrencyCode(set)
                Toast.makeText(activity, "Added", Toast.LENGTH_LONG).show()
                extCurrencyCode.setText("")

            }
        }
        btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_addCurrencyFragment_to_homeFragment)
        }


    }

}