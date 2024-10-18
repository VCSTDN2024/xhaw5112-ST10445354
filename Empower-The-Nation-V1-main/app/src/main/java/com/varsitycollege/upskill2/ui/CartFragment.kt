package com.varsitycollege.upskill2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.varsitycollege.upskill2.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val sharedCartViewModel: SharedCartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView with the cart items
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // Observe the cart data from the shared ViewModel
        sharedCartViewModel.cart.observe(viewLifecycleOwner) { cartItems ->
            // Set up the adapter with the cart items
            binding.recyclerView.adapter = CourseAdapter(cartItems, cartItems) { course ->
                // No add to cart action needed in CartFragment
            }

            // Update the total price and savings whenever the cart changes
            updateTotalPrice(cartItems)
            updateCompletePurchaseButtonState(cartItems)
        }

        // Clear cart button click listener
        binding.clearCartButton.setOnClickListener {
            sharedCartViewModel.clearCart() // Clear the cart when the button is clicked
        }

        // Complete purchase button click listener
        binding.completePurchaseButton.setOnClickListener {
            val selectedPaymentMethod = sharedCartViewModel.paymentMethod.value
            if (selectedPaymentMethod != null) {
                showPurchaseSuccessDialog()  // Show success alert dialog on complete purchase
            } else {
                showAlert("Please enter a payment method.") // Alert if no payment method entered
            }
        }
    }

    private fun updateTotalPrice(cartItems: List<Course>) {
        val originalPrice = cartItems.sumOf { it.price }
        val vatRate = 0.15

        // Calculate discount percentage based on the number of courses
        val discountPercentage = when (cartItems.size) {
            2 -> 5
            3 -> 10
            in 4..Int.MAX_VALUE -> 15
            else -> 0
        }

        // Apply the discount (exclusive of VAT)
        val discountedPrice = originalPrice * (1 - discountPercentage / 100.0)

        // VAT-exclusive price
        val vatExclusivePrice = discountedPrice / (1 + vatRate)
        val vatAmount = discountedPrice - vatExclusivePrice
        val savings = originalPrice - discountedPrice

        // Update total price text (VAT-exclusive)
        binding.totalPriceTextView.text = String.format("Total (Excl. VAT): R %.2f", vatExclusivePrice)

        // Display savings and discount percentage if applicable
        if (savings > 0) {
            binding.savingsTextView.visibility = View.VISIBLE
            binding.savingsTextView.text = String.format("You save: R %.2f (%d%% discount)", savings, discountPercentage)
        } else {
            binding.savingsTextView.visibility = View.GONE
        }

        // Display VAT details
        binding.vatTextView.visibility = View.VISIBLE
        binding.vatTextView.text = String.format("VAT (15%%): R %.2f", vatAmount)

        // Display total price including VAT
        binding.totalPriceWithVatTextView.visibility = View.VISIBLE
        binding.totalPriceWithVatTextView.text = String.format("Total (Incl. VAT): R %.2f", discountedPrice)
    }

    private fun updateCompletePurchaseButtonState(cartItems: List<Course>) {
        binding.completePurchaseButton.isEnabled = cartItems.isNotEmpty()
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Alert")
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun showPurchaseSuccessDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Purchase Successful")
        builder.setMessage("Your purchase has been completed successfully.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            sharedCartViewModel.clearCart() // Clear the cart after the purchase
        }
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
