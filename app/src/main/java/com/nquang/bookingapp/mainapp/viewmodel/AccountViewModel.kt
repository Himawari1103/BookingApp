package com.nquang.bookingapp.mainapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nquang.bookingapp.model.UserModel

class AccountViewModel : ViewModel() {
    var userModel = mutableStateOf<UserModel?>(null)

}