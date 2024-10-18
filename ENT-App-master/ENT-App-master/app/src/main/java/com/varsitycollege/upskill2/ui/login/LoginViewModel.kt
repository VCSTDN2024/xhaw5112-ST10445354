package com.varsitycollege.upskill2.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Sign Up Fragment"
    }
    val text: LiveData<String> = _text
}