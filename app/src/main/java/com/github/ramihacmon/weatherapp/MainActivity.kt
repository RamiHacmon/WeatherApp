package com.github.ramihacmon.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherDaysList = findViewById<RecyclerView>(R.id.weatherDayslist)
        val adapter = WeatherDaysAdapter()

        weatherDaysList.adapter = adapter

    }

    class WeatherDaysAdapter: RecyclerView.Adapter<WeatherDaysAdapter.WeatherDaysViewHolder>() {

        var weatherDays: List<WeatherDay> = listOf(
                WeatherDay(Date(), "Sunny", "22"),
                WeatherDay(Date(), "Cloudy", "18"),
                WeatherDay(Date(), "Sunny", "26"),
                WeatherDay(Date(), "Rainy","10")
        )

        class WeatherDaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val weatherIcon: ImageView = itemView.findViewById(R.id.sunIcon)
            val condition: TextView = itemView.findViewById(R.id.condition)
            val dateView: TextView = itemView.findViewById(R.id.date)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDaysViewHolder {
            return WeatherDaysViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.weather_day_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: WeatherDaysViewHolder, position: Int) {
            val weatherDay = weatherDays[position]

            holder.condition.text = weatherDay.condition
            holder.dateView.text = weatherDay.date.toString()
            when (weatherDay.condition){
                "Sunny" -> holder.weatherIcon.setImageResource(R.drawable.ic_sun_line)
                "Cloudy" -> holder.weatherIcon.setImageResource(R.drawable.ic_cloudy)
                "Rainy" -> holder.weatherIcon.setImageResource((R.drawable.ic_rain))
            }
            holder.weatherIcon.setImageResource(R.drawable.ic_sun_cloudy)
        }

        override fun getItemCount(): Int {
            return weatherDays.size
        }
    }

    data class WeatherDay(val date: Date, val condition: String, val degrees: String)
}