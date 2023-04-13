package com.example.powow.ui.fragments.dashboard

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powow.adapters.calendar.AdapterSun
import com.example.powow.adapters.calendar.CalendarAdapter
import com.example.powow.databinding.FragmentCalendarBinding
import com.example.powow.models.calendar.ItemData
import com.example.powow.utils.CalendarUtils
import com.example.powow.utils.CalendarUtils.Companion.daysInWeekArray
import java.time.LocalDate

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    lateinit var adapterSun: AdapterSun
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        CalendarUtils.selectedDate = LocalDate.now()
        setWeekView()
        initRecyclerSun()
//        initRecyclerLoader()
        initClicks()
        return binding.root
    }

    private fun initRecyclerSun() {
        val dataSun = ArrayList<ItemData>()
        dataSun.add(ItemData("1"))
        dataSun.add(ItemData(" "))
        dataSun.add(ItemData(" "))
        dataSun.add(ItemData("1"))
        dataSun.add(ItemData("1"))
        dataSun.add(ItemData("1"))
        dataSun.add(ItemData("1"))
        dataSun.add(ItemData(" "))
        dataSun.add(ItemData( " "))
        dataSun.add(ItemData("1"))
        dataSun.add(ItemData("1"))

        val dataMon = ArrayList<ItemData>()
        dataMon.add(ItemData(" "))
        dataMon.add(ItemData("1"))
        dataMon.add(ItemData(" "))
        dataMon.add(ItemData(" "))
        dataMon.add(ItemData("1"))
        dataMon.add(ItemData("1"))
        dataMon.add(ItemData("1"))
        dataMon.add(ItemData("1"))
        dataMon.add(ItemData(  " "))
        dataMon.add(ItemData(" "))
        dataMon.add(ItemData("1"))

        val dataTue = ArrayList<ItemData>()
        dataTue.add(ItemData("1"))
        dataTue.add(ItemData("1"))
        dataTue.add(ItemData("1"))
        dataTue.add(ItemData(" "))
        dataTue.add(ItemData(" "))
        dataTue.add(ItemData("1"))
        dataTue.add(ItemData(" "))
        dataTue.add(ItemData(" "))
        dataTue.add(ItemData(  "1"))
        dataTue.add(ItemData(" "))
        dataTue.add(ItemData("1"))

        val dataWed = ArrayList<ItemData>()
        dataWed.add(ItemData("1"))
        dataWed.add(ItemData(" "))
        dataWed.add(ItemData("1"))
        dataWed.add(ItemData(" "))
        dataWed.add(ItemData("1"))
        dataWed.add(ItemData(" "))
        dataWed.add(ItemData("1"))
        dataWed.add(ItemData(" "))
        dataWed.add(ItemData(  "1"))
        dataWed.add(ItemData(" "))
        dataWed.add(ItemData("1"))

        val dataThu = ArrayList<ItemData>()
        dataThu.add(ItemData(" "))
        dataThu.add(ItemData("1"))
        dataThu.add(ItemData("1"))
        dataThu.add(ItemData("1"))
        dataThu.add(ItemData("1"))
        dataThu.add(ItemData(" "))
        dataThu.add(ItemData("1"))
        dataThu.add(ItemData("1"))
        dataThu.add(ItemData(  "1"))
        dataThu.add(ItemData(" "))
        dataThu.add(ItemData("1"))

        val dataFri = ArrayList<ItemData>()
        dataFri.add(ItemData("1"))
        dataFri.add(ItemData("1"))
        dataFri.add(ItemData("1"))
        dataFri.add(ItemData("1"))
        dataFri.add(ItemData(" "))
        dataFri.add(ItemData(" "))
        dataFri.add(ItemData(" "))
        dataFri.add(ItemData(" "))
        dataFri.add(ItemData(  "1"))
        dataFri.add(ItemData("1"))
        dataFri.add(ItemData("1"))

        val dataSat = ArrayList<ItemData>()
        dataSat.add(ItemData("1"))
        dataSat.add(ItemData("1"))
        dataSat.add(ItemData(" "))
        dataSat.add(ItemData(" "))
        dataSat.add(ItemData("1"))
        dataSat.add(ItemData(" "))
        dataSat.add(ItemData(" "))
        dataSat.add(ItemData(" "))
        dataSat.add(ItemData(  "1"))
        dataSat.add(ItemData("1"))
        dataSat.add(ItemData("1"))

        val dataLoad = ArrayList<ItemData>()
        dataLoad.add(ItemData("35"))
        dataLoad.add(ItemData("35"))
        dataLoad.add(ItemData("35"))
        dataLoad.add(ItemData("45"))
        dataLoad.add(ItemData("45"))
        dataLoad.add(ItemData("45"))
        dataLoad.add(ItemData("45"))
        dataLoad.add(ItemData("45"))
        dataLoad.add(ItemData( "45"))
        dataLoad.add(ItemData("35"))
        dataLoad.add(ItemData("35"))

        adapterSun = AdapterSun(requireContext(),dataSun,dataMon,dataTue,dataWed,dataThu,dataFri,dataSat,dataLoad) { items, position ->

        }
        binding.recyclerSun.adapter = adapterSun
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initClicks() {
        binding.arrowBack.setOnClickListener {
            CalendarUtils.selectedDate = CalendarUtils.selectedDate!!.minusWeeks(1)
            setWeekView()
        }
        binding.arrowForward.setOnClickListener {
            CalendarUtils.selectedDate = CalendarUtils.selectedDate!!.plusWeeks(1)
            setWeekView()
        }
    }

    private fun setWeekView() {
        val days: ArrayList<LocalDate?> = daysInWeekArray(CalendarUtils.selectedDate!!)

        val calendarAdapter = CalendarAdapter(days, this){ dataItem,localDate ->
            CalendarUtils.selectedDate = localDate
            setWeekView()
        }
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(requireContext(), 7)
        binding.calendarRecyclerView.layoutManager = layoutManager
        binding.calendarRecyclerView.adapter = calendarAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}