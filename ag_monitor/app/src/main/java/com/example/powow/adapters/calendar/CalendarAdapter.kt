package com.example.powow.adapters.calendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.powow.databinding.ExampleCalendarDayBinding
import com.example.powow.ui.fragments.dashboard.CalendarFragment
import com.example.powow.utils.CalendarUtils
import java.time.LocalDate

class CalendarAdapter(
    val dataAll: ArrayList<LocalDate?>,
    val context: CalendarFragment,
    val onItemClick: (Int, date:LocalDate) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExampleCalendarDayBinding.inflate(inflater, parent, false)
        return CalendarVH(binding)
    }

    override fun getItemCount(): Int = dataAll.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val date: LocalDate = dataAll[position]!!
        holder as CalendarVH
        holder.apply {
            binding.apply {
                if (date == null) exSevenDateText.text = "" else {
                    exSevenDateText.text = date.dayOfMonth.toString()
                    if (date == CalendarUtils.selectedDate) {
                        exSevenDateText.setTextColor(Color.parseColor("#000000"))
                        exSevenSelectedView.visibility = VISIBLE
                    }else{
                        exSevenDateText.setTextColor(Color.parseColor("#838383"))
                        exSevenSelectedView.visibility = GONE
                    }
                }
                itemView.setOnClickListener {
                    onItemClick(adapterPosition, dataAll[adapterPosition]!!)
                }
            }
        }
    }
}

class CalendarVH(val binding: ExampleCalendarDayBinding) : RecyclerView.ViewHolder(binding.root)
