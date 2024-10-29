package com.ntc.themoneyconverter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntc.themoneyconverter.R
import com.ntc.themoneyconverter.databinding.FragmentSelectSymbolBinding
import com.ntc.themoneyconverter.viewmodels.SelectSymbolViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "FragmentConvert"

class FragmentSelectSymbol : Fragment() {
    private var _binding: FragmentSelectSymbolBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null, is the view visible ?"
        }

    private val selectSymbolViewModel: SelectSymbolViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectSymbolBinding.inflate(inflater, container, false)
        binding.symbolList.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                selectSymbolViewModel.currencies.collect { currencies ->
                    binding.symbolList.adapter = SymbolListAdapter(currencies)
                }

            }
        }
    }

}