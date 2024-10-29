package com.ntc.themoneyconverter.ui

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.ntc.themoneyconverter.R
import com.ntc.themoneyconverter.data.Currency
import com.ntc.themoneyconverter.databinding.ListItemSymbolBinding

class SymbolViewHolder (
    private val binding : ListItemSymbolBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(currency: Currency) {
        binding.symbolCode.text = currency.code
        binding.symbolName.text = currency.name
        binding.symbolItem.setOnClickListener {
            val from = FragmentManager.findFragment<FragmentSelectSymbol>(binding.root).arguments?.getBoolean("from")?: false
            val bundle = Bundle()
            val prefs = binding.root.context.getSharedPreferences("symbols", MODE_PRIVATE).edit()
            if (from) {
                prefs.putString("fromSymbol", currency.code)
            }
            else {
                if (currency.code != "") {
                    prefs.putString("toSymbol", currency.code)
                }
            }
            prefs.apply()
            Navigation.findNavController(binding.root).navigate(R.id.fragmentConvert, bundle)
        }
    }
}

class SymbolListAdapter(
    private val symbols: List<Currency>
) : RecyclerView.Adapter<SymbolViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymbolViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemSymbolBinding.inflate(inflater, parent, false)
        return SymbolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SymbolViewHolder, position: Int) {
        val item = symbols[position]
        holder.bind(item)
    }

    override fun getItemCount() = symbols.size
}