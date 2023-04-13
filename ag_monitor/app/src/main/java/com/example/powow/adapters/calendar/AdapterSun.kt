package com.example.powow.adapters.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.powow.databinding.RecyclerIrrigationBinding
import com.example.powow.models.calendar.ItemData
import kotlin.collections.ArrayList

class AdapterSun(
    val context: Context,
    val dataSun: ArrayList<ItemData>,
    val dataMon: ArrayList<ItemData>,
    val dataTue: ArrayList<ItemData>,
    val dataWed: ArrayList<ItemData>,
    val dataThu: ArrayList<ItemData>,
    val dataFri: ArrayList<ItemData>,
    val dataSat: ArrayList<ItemData>,
    val dataLoad: ArrayList<ItemData>,
    val onItemClick: (String, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerIrrigationBinding.inflate(inflater, parent, false)
        return IrrigationVH(binding)
    }

    override fun getItemCount(): Int = 11

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItemSun = dataSun[position]
        val dataItemMon = dataMon[position]
        val dataItemTue = dataTue[position]
        val dataItemWed = dataWed[position]
        val dataItemThu = dataThu[position]
        val dataItemFri = dataFri[position]
        val dataItemSat = dataSat[position]
        val dataItemLoad = dataLoad[position]
        holder as IrrigationVH
        holder.apply {
            binding.apply {
                textSun.text = dataItemSun.items
                textMon.text = dataItemMon.items
                textTue.text = dataItemTue.items
                textWed.text = dataItemWed.items
                textThu.text = dataItemThu.items
                textFri.text = dataItemFri.items
                textSat.text = dataItemSat.items
                textPercentage.text = dataItemLoad.items
                progressBar.progress = Integer.parseInt(dataItemLoad.items)
            }
        }
    }

}

class IrrigationVH(val binding: RecyclerIrrigationBinding) : RecyclerView.ViewHolder(binding.root) {}
