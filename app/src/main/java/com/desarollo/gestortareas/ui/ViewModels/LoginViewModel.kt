package com.desarollo.gestortareas.ui.ViewModels

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Notification

class LogInViewModel: ViewModel() {
    private val _user = MutableLiveData<String>()
    val user : LiveData<String> = _user

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    fun onChange(user: String, password: String) {
        _user.value = user
        _password.value = password
    }

    fun buttonEnable(user: String, password: String):Boolean {
        return password.length>7 && user.isNotBlank()
    }

}