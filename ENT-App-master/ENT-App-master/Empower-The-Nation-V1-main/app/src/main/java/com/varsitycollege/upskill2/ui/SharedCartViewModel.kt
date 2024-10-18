package com.varsitycollege.upskill2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedCartViewModel : ViewModel() {

    private val _cart = MutableLiveData<MutableList<Course>>(mutableListOf())
    val cart: LiveData<MutableList<Course>> get() = _cart

    private val _paymentMethod = MutableLiveData<PaymentMethod?>()
    val paymentMethod: LiveData<PaymentMethod?> get() = _paymentMethod

    fun addToCart(course: Course) {
        _cart.value?.add(course)
        _cart.value = _cart.value // trigger LiveData observers
    }

    fun clearCart() {
        _cart.value?.clear()
        _cart.value = _cart.value // trigger LiveData observers
    }

    fun savePaymentMethod(cardNumber: String, expiryDate: String, cvv: String) {
        _paymentMethod.value = PaymentMethod(cardNumber, expiryDate, cvv)
    }


}
