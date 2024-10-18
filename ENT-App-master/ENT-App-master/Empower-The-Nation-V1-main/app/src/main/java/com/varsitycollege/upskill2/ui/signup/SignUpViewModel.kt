package com.varsitycollege.upskill2.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Sign Up Fragment"
    }
    val text: LiveData<String> = _text
}
