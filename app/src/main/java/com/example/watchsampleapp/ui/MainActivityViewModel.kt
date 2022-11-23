package com.example.watchsampleapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _counterValue = MutableLiveData(0)
    val counterValue: LiveData<Int> = _counterValue

    fun incrementCounter() {
        _counterValue.value = _counterValue.value!! + 1
    }

    fun decrementCounter() {
        if (_counterValue.value!! > 0) {
            _counterValue.value = _counterValue.value!! - 1
        }
    }

    fun resetCounter() {
        _counterValue.value = 0
    }
}
