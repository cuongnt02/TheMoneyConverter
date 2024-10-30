package com.ntc.themoneyconverter.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ntc.themoneyconverter.R
import com.ntc.themoneyconverter.databinding.FragmentConvertBinding
import com.ntc.themoneyconverter.viewmodels.ConvertViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "FragmentConvert"

class FragmentConvert : Fragment() {
    private var _binding: FragmentConvertBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val convertViewModel : ConvertViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConvertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                val prefs = activity?.getSharedPreferences("symbols", MODE_PRIVATE)
                val fromSymbol = prefs?.getString("fromSymbol", "")
                val toSymbol = prefs?.getString("toSymbol", "")
                if (fromSymbol != "") {
                    if (fromSymbol != null && convertViewModel.convertFromSymbol.value != fromSymbol) {
                        convertViewModel.convertFromSymbol.value = fromSymbol
                    }
                }
                if (toSymbol != "") {
                    if (toSymbol != null && convertViewModel.convertToSymbol.value != toSymbol) {
                        convertViewModel.convertToSymbol.value = toSymbol
                    }
                }
                binding.textConvertFrom.text = convertViewModel.convertFromSymbol.value
                binding.buttonSelectSymbolToText.text = convertViewModel.convertToSymbol.value
            }
        }


        binding.buttonSelectSymbolFrom.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("from", true)
            findNavController().navigate(R.id.fragmentSelectSymbol, bundle)
        }

        binding.buttonSelectSymbolTo.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("from", false)
            findNavController().navigate(R.id.fragmentSelectSymbol, bundle)
        }

        binding.editTextConvert.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewLifecycleOwner.lifecycleScope.launch {
                    convertViewModel.currentAmount.value = s.toString().toDouble()
                    convertViewModel.fetchAndConvert()

                    binding.infoLog.text = convertViewModel.logMessage.value
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {





            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}