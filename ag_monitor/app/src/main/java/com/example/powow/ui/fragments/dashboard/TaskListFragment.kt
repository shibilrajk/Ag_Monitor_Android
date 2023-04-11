package com.example.powow.ui.fragments.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.powow.adapters.AdapterTaskList
import com.example.powow.databinding.FragmentTaskListBinding
import com.example.powow.models.taskList.TaskListData
import java.util.ArrayList

class TaskListFragment : Fragment() {
    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: AdapterTaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        initRecyclerView()
//        binding.layoutError.layoutError.visibility = VISIBLE
        return binding.root
    }

    private fun initRecyclerView() {
        var dataAll = ArrayList<TaskListData>()
        dataAll.add(
            TaskListData(
                "https://pbs.twimg.com/media/FlvScPNWIAAhGGW.jpg",
                "Field 1 North Irrigation Set",
                "35 hours for the week"
            )
        )
        dataAll.add(
            TaskListData(
                "https://pbs.twimg.com/media/FlvScPNWIAAhGGW.jpg",
                "Field 1 North Irrigation Set",
                "35 hours for the week"
            )
        )
        dataAll.add(
            TaskListData(
                "https://pbs.twimg.com/media/FlvScPNWIAAhGGW.jpg",
                "Field 1 North Irrigation Set",
                "35 hours for the week"
            )
        )
        dataAll.add(
            TaskListData(
                "https://pbs.twimg.com/media/FlvScPNWIAAhGGW.jpg",
                "Field 1 North Irrigation Set",
                "35 hours for the week"
            )
        )
        dataAll.add(
            TaskListData(
                "https://pbs.twimg.com/media/FlvScPNWIAAhGGW.jpg",
                "Field 1 North Irrigation Set",
                "35 hours for the week"
            )
        )
        dataAll.add(
            TaskListData(
                "https://pbs.twimg.com/media/FlvScPNWIAAhGGW.jpg",
                "Field 1 North Irrigation Set",
                "35 hours for the week"
            )
        )
        dataAll.add(
            TaskListData(
                "https://pbs.twimg.com/media/FlvScPNWIAAhGGW.jpg",
                "Field 1 North Irrigation Set",
                "35 hours for the week"
            )
        )
        dataAll.add(
            TaskListData(
                "https://pbs.twimg.com/media/FlvScPNWIAAhGGW.jpg",
                "Field 1 North Irrigation Set",
                "35 hours for the week"
            )
        )
        dataAll.add(
            TaskListData(
                "https://pbs.twimg.com/media/FlvScPNWIAAhGGW.jpg",
                "Field 1 North Irrigation Set",
                "35 hours for the week"
            )
        )
        dataAll.add(
            TaskListData(
                "https://pbs.twimg.com/media/FlvScPNWIAAhGGW.jpg",
                "Field 1 North Irrigation Set",
                "35 hours for the week"
            )
        )

        adapter = AdapterTaskList(requireContext(), dataAll) { items, position ->

        }
        binding.recyclerTaskList.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TaskListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}