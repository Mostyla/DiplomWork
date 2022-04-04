package com.example.myweatherprogramm.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myweatherprogramm.R
import com.example.myweatherprogramm.model.Daily
import com.example.myweatherprogramm.databinding.WeatherListItemBinding
import java.text.SimpleDateFormat
import java.util.*

class WeatherRcViewAdapter(
    private val accessoriesList: List<Daily>
) : RecyclerView.Adapter<WeatherRcViewAdapter.WeatherHolder>() {

    inner class WeatherHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = WeatherListItemBinding.bind(item)

        fun bind(daily: Daily) = with(binding) {
            Glide.with(itemView.context).load("http://openweathermap.org/img/wn/${daily.weather[0].icon}@2x.png").into(binding.daysIconsImageView)
            tempDaysTextView.text = daily.temp.day.toInt().toString() + "°C"
            daysOfWeekTextView.text = convertLongToTime(daily.dt * 1000)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item, parent, false)
        return WeatherHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(accessoriesList[position])
    }

    override fun getItemCount(): Int {
        return accessoriesList.size
    }

    @SuppressLint("SimpleDateFormat")
    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("E", Locale.ENGLISH)
        return format.format(date)
    }
}