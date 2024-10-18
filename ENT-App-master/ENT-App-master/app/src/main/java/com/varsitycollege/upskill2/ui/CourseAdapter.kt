package com.varsitycollege.upskill2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.varsitycollege.upskill2.R

class CourseAdapter(
    private val courses: List<Course>,
    private val cart: List<Course>,
    private val addToCartCallback: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseName: TextView = itemView.findViewById(R.id.courseName)
        val courseDescription: TextView = itemView.findViewById(R.id.courseDescription)
        val coursePrice: TextView = itemView.findViewById(R.id.coursePrice)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.courseName.text = course.name
        holder.courseDescription.text = course.description
        holder.coursePrice.text = "R ${course.price}"

        // Disable the "Add to Cart" button if the course is already in the cart
        if (cart.contains(course)) {
            holder.addToCartButton.isEnabled = false
            holder.addToCartButton.text = "Already in Cart"
        } else {
            holder.addToCartButton.isEnabled = true
            holder.addToCartButton.text = "Add to Cart"
            holder.addToCartButton.setOnClickListener {
                addToCartCallback(course)
            }
        }
    }

    override fun getItemCount(): Int = courses.size
}
