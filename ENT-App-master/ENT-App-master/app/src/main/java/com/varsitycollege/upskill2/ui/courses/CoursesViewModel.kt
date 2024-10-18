package com.varsitycollege.upskill2.ui.courses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoursesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Courses Fragment"
    }
    val text: LiveData<String> = _text
}