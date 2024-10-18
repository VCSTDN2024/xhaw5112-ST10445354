package com.varsitycollege.upskill2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.varsitycollege.upskill2.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gardeningButton: Button = view.findViewById(R.id.gardeningBtn)
        gardeningButton.setOnClickListener {
            // Navigate to CoursesFragment using NavController
            findNavController().navigate(R.id.nav_courses)
        }

        val cookingButton: Button = view.findViewById(R.id.cookingBtn)
        cookingButton.setOnClickListener {
            // Navigate to CoursesFragment using NavController
            findNavController().navigate(R.id.nav_courses)
        }

        val sewingButton: Button = view.findViewById(R.id.sewingBtn)
        sewingButton.setOnClickListener {
            // Navigate to CoursesFragment using NavController
            findNavController().navigate(R.id.nav_courses)
        }

        val landscappingButton: Button = view.findViewById(R.id.landscappingBtn)
        landscappingButton.setOnClickListener {
            // Navigate to CoursesFragment using NavController
            findNavController().navigate(R.id.nav_courses)
        }

        val lifeskillButton: Button = view.findViewById(R.id.lifeskillsBtn)
        lifeskillButton.setOnClickListener {
            // Navigate to CoursesFragment using NavController
            findNavController().navigate(R.id.nav_courses)
        }

        val childmindingButton: Button = view.findViewById(R.id.childmindingBtn)
        childmindingButton.setOnClickListener {
            // Navigate to CoursesFragment using NavController
            findNavController().navigate(R.id.nav_courses)
        }

        val firstaidButton: Button = view.findViewById(R.id.firstaidBtn)
        firstaidButton.setOnClickListener {
            // Navigate to CoursesFragment using NavController
            findNavController().navigate(R.id.nav_courses)
        }
    }
}
