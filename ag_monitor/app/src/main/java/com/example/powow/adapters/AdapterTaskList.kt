package com.example.powow.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.powow.R
import com.example.powow.databinding.RecyclerTaskListItemBinding
import com.example.powow.helpers.getGlideProgress
import com.example.powow.models.taskList.TaskListData
import java.util.ArrayList

class AdapterTaskList(
    val context: Context,
    val dataAll: ArrayList<TaskListData>,
    val onItemClick: (String, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerTaskListItemBinding.inflate(inflater, parent, false)
        return TaskListVH(binding)
    }

    override fun getItemCount(): Int = dataAll.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = dataAll[position]
        holder as TaskListVH
        holder.apply {
            binding.apply {
                textFirstText.text = dataItem.imigrationSet
                textSecondText.text = dataItem.hoursOfWeek
                Glide.with(context).load(dataItem.images)
                    .placeholder(context.getGlideProgress())
                    /*.error(R.drawable.logo_bg)*/.transition(
                        DrawableTransitionOptions.withCrossFade()
                    ).into(imgTask)
            }
        }
    }
}

class TaskListVH(val binding: RecyclerTaskListItemBinding) : RecyclerView.ViewHolder(binding.root) {}
