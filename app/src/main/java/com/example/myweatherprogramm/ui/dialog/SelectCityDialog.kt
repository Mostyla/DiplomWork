package com.example.myweatherprogramm.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweatherprogramm.ui.adapter.CityAdapter
import com.example.myweatherprogramm.data.CityHelper
import com.example.myweatherprogramm.model.CityModel
import com.example.myweatherprogramm.databinding.DialogSelectCityBinding

class SelectCityDialog(private val mContext: Context, private val onCitySelectListener: OnCitySelectListener):
    Dialog(mContext, android.R.style.Theme_NoTitleBar_Fullscreen) {

    private lateinit var binding: DialogSelectCityBinding
    private lateinit var adapter: CityAdapter

    init {
        setCancelable(true)
        setCanceledOnTouchOutside(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogSelectCityBinding.inflate(LayoutInflater.from(mContext))
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        setContentView(binding.root)

        initView()

        initActions()
    }

    private fun initView() {
        binding.rvCity.layoutManager = LinearLayoutManager(mContext)
        adapter = CityAdapter(object : OnCitySelectListener {
            override fun onCitySelected(cityModel: CityModel) {
                onCitySelectListener.onCitySelected(cityModel)
                dismiss()
            }
        })
        binding.rvCity.adapter = adapter
    }

    private fun initActions() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }

        binding.etCity.doOnTextChanged { text, start, before, count ->
            filterCountry(text.toString())
        }
    }

    private fun filterCountry(text: String) {
        val filteredList: ArrayList<CityModel> = ArrayList()
        for (item in CityHelper.populateCity()) {
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        adapter.filterList(filteredList)
    }

    interface OnCitySelectListener {
        fun onCitySelected(cityModel: CityModel)
    }

}