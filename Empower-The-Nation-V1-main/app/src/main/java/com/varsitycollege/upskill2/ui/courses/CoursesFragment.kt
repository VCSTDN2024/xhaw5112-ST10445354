package com.varsitycollege.upskill2.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.varsitycollege.upskill2.R
import com.varsitycollege.upskill2.databinding.FragmentCoursesBinding
import com.varsitycollege.upskill2.ui.Course
import com.varsitycollege.upskill2.ui.CourseAdapter
import com.varsitycollege.upskill2.ui.SharedCartViewModel

class CoursesFragment : Fragment() {

    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!
    private val sharedCartViewModel: SharedCartViewModel by activityViewModels()

    private val courses = listOf(
        Course(1, "Child Minding", "Purpose: To provide basic child and baby. \n \n " +
                "Duration: 6 weeks \n \n" +
                "Content: \n" +
                "- Birth to six-month old baby needs \n" +
                "- Seven-month to one year old needs \n" +
                "- Toddler needs \n" +
                "- Educational toys", 750.00),
        Course(2, "Cooking", "Purpose: To prepare and cook nutritious family meals. \n \n " +
                "Duration: 6 weeks \n \n" +
                "Content: \n" +
                "- Nutritional requirements for a healthy body \n" +
                "- Types of protein, carbohydrates and vegetables \n" +
                "- Planning meals \n" +
                "- Preparation and cooking of meals", 750.00),
        Course(3, "Garden Maintenance", "Purpose: To provide basic knowledge of watering, pruning and planting in a domestic garden. \n \n" +
                "Duration: 6 weeks \n \n" +
                "Content: \n" +
                "- Water restrictions and the watering requirements of indigenous and exotic plants \n" +
                "- Pruning and propagation of plants \n" +
                "- Planting techniques for different plant types", 750.00),
        Course(4, "First Aid", "Purpose: To provide first aid awareness and basic life support. \n \n " +
                "Duration: 6 months \n \n" +
                "Content: \n" +
                "- Wounds and bleeding \n" +
                "- Burns and fractures \n" +
                "- Emergency scene management \n" +
                "- Cardio-Pulmonary Resuscitation (CPR) \n" +
                "- Respiratory distress e.g., Choking, blocked airway", 1500.00),
        Course(5, "Sewing", "Purpose: To provide alterations and new garment tailoring services. \n \n " +
                "Duration: 6 months \n \n" +
                "Content: \n" +
                "- Types of stiches \n" +
                "- Threading a sewing machine \n" +
                "- Sewing buttons, zips, hems and seams \n" +
                "- Alterations \n" +
                "- Designing and sewing new garments", 1500.00),
        Course(6, "Landscaping", "Purpose: To provide landscapping services for new and established gardens. \n \n" +
                "Duration: 6 months \n \n" +
                "Content: \n" +
                "- Indigenous and exotic plants and trees \n" +
                "- Fixed structures (fountains, statues, benches, tables, built-in braai) \n" +
                "- Balancing of plants and trees in a garden \n" +
                "- Aesthetics of plant shapes and colours \n" +
                "- Garden layout", 1500.00),
        Course(7, "Life Skills", "Purpose: To provide basic skills to navigate basic life necessities. \n \n" +
                "Duration: 6 months \n \n" +
                "Content: \n" +
                "- Opening a bank account \n" +
                "- Basic labour law (know your rights \n" +
                "- Basic reading and writing literacy \n" +
                "- Basic numeric literacy", 1500.00),
        // Add more courses here
    )

    private val cart = mutableListOf<Course>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedCartViewModel.cart.observe(viewLifecycleOwner) { cartItems ->
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CourseAdapter(courses, cartItems) { course ->
                    sharedCartViewModel.addToCart(course)
                }
            }

            // Update the FAB badge count
            val cartSize = cartItems.size
            binding.cartItemCount.text = cartSize.toString()
        }

        // Handle FAB click to navigate to CartFragment
        binding.fabCart.setOnClickListener {
            findNavController().navigate(R.id.action_coursesFragment_to_cartFragment)
        }

        val shoppingCart: FloatingActionButton = view.findViewById(R.id.fabCart)
        shoppingCart.setOnClickListener {
            // Navigate to CoursesFragment using NavController
            findNavController().navigate(R.id.nav_cart)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}