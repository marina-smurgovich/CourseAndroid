package com.yandex.smur.marina.task8_weather.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yandex.smur.marina.task8_weather.R
import com.yandex.smur.marina.task8_weather.presenter.WeatherListItemModel
import kotlinx.android.synthetic.main.item_recyclerview_weather.view.*

class WeatherListAdapter : RecyclerView.Adapter<WeatherListAdapter.WeatherListItemHolder>() {

    private val weatherList = mutableListOf<WeatherListItemModel>()
    private var units: String = "metric"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListItemHolder =
            WeatherListItemHolder(
                    itemView = parent.run {
                        LayoutInflater.from(context).inflate(R.layout.item_recyclerview_weather, this, false)
                    }
            )

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherListItemHolder, position: Int) {
        holder.bind(weatherList[position], units)
    }

    fun updateWeatherList(list: List<WeatherListItemModel>, inits: String) {
        weatherList.apply {
            clear()
            addAll(list)
        }
        units = inits
        notifyDataSetChanged()
    }


    class WeatherListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(weatherListItemModel: WeatherListItemModel, units: String) {


            with(weatherListItemModel) {
                itemView.apply {
                    Glide.with(itemView.context)
                            .load(urlImage)
                            .into(itemView.ImageViewImage)

                    textViewTime.text = time.toString()
                    textViewDescription.text = description
                    textViewDegrees.text = tempConvert(temperature.toString(), units)

                }
            }
        }

        fun tempConvert(temp: String, units: String): String = if (units == "metric") "$temp °C" else "$temp °F"

    }
}
