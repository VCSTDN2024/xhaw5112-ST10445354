package com.varsitycollege.upskill2.ui.paymentmethod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaymentmethodViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Payment Fragment"
    }
    val text: LiveData<String> = _text
}