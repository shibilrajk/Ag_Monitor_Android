package com.example.powow.ui.fragments.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powow.adapters.calendar.CalendarAdapter
import com.example.powow.databinding.FragmentCalendarBinding
import com.example.powow.utils.CalendarUtils
import com.example.powow.utils.CalendarUtils.Companion.daysInWeekArray
import java.time.LocalDate

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        CalendarUtils.selectedDate = LocalDate.now()
        setWeekView()
        initClicks()
        return binding.root
    }

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