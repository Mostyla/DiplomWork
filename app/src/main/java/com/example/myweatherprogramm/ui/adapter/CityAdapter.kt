package com.example.myweatherprogramm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherprogramm.ui.dialog.SelectCityDialog
import com.example.myweatherprogramm.data.CityHelper
import com.example.myweatherprogramm.model.CityModel
import com.example.myweatherprogramm.databinding.ItemCityBinding

class CityAdapter(private val onCitySelectListener: SelectCityDialog.OnCitySelectListener) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var cities = CityHelper.populateCity()

    inner class CityViewHolder(itemView: ItemCityBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val tv_city = itemView.tvCity

        fun onBind(position: Int) {
            val city = cities[position]
            tv_city.text = city.name
            itemView.setOnClickListener {
                onCitySelectListener.onCitySelected(city)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder(
            ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun filterList(filteredList: ArrayList<CityModel>) {
        cities = filteredList
        notifyDataSetChanged()
    }

}