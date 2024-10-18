package com.varsitycollege.upskill2.ui.paymentmethod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.varsitycollege.upskill2.R
import com.varsitycollege.upskill2.ui.SharedCartViewModel

class PaymentmethodFragment : Fragment() {

    private lateinit var cardNumberEditText: EditText
    private lateinit var expiryDateEditText: EditText
    private lateinit var cvvEditText: EditText
    private lateinit var saveButton: Button

    private val sharedCartViewModel: SharedCartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_paymentmethod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardNumberEditText = view.findViewById(R.id.editTextCardNumber)
        expiryDateEditText = view.findViewById(R.id.editTextExpiryDate)
        cvvEditText = view.findViewById(R.id.editTextCVV)
        saveButton = view.findViewById(R.id.buttonSavePaymentMethod)

        saveButton.setOnClickListener {
            savePaymentMethod()
        }
    }

    private fun savePaymentMethod() {
        val cardNumber = cardNumberEditText.text.toString()
        val expiryDate = expiryDateEditText.text.toString()
        val cvv = cvvEditText.text.toString()

        if (cardNumber.isBlank() || expiryDate.isBlank() || cvv.isBlank()) {
            showError("Please fill out all fields.")
            return
        }

        // Use the ViewModel to save payment method
        sharedCartViewModel.savePaymentMethod(cardNumber, expiryDate, cvv)

        // Show success message
        showSuccess("Payment method saved successfully.")
    }

    private fun showError(message: String) {
        // Show an error message to the user
        // You could use a Toast, Snackbar, or a dialog
    }

    private fun showSuccess(message: String) {
        // Show a success message to the user
        // You could use a Toast, Snackbar, or a dialog
    }
}
