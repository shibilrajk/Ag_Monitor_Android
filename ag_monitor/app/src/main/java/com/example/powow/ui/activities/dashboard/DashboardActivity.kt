package com.example.powow.ui.activities.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.powow.R
import com.example.powow.databinding.ActivityDashboardBinding
import com.example.powow.ui.fragments.dashboard.CalendarFragment
import com.example.powow.ui.fragments.dashboard.ChatFragment
import com.example.powow.ui.fragments.dashboard.MapFragment
import com.example.powow.ui.fragments.dashboard.TaskListFragment

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
    }

    private fun initNavController() {
        val mMapFragment = MapFragment()
        val mTaskListFragment = TaskListFragment()
        val mCalendarFragment = CalendarFragment()
        val mChatFragment = ChatFragment()
        setCurrentFragment(mTaskListFragment)
        binding.bottomNavigationView.selectedItemId = R.id.list
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.map -> setCurrentFragment(mMapFragment)
                R.id.list -> setCurrentFragment(mTaskListFragment)
                R.id.calendar -> setCurrentFragment(mCalendarFragment)
                R.id.chat -> setCurrentFragment(mChatFragment)

            }
            true
        }
    }

    private fun setCurrentFragment(mFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainContainer, mFragment)
            commit()
        }
    }
}