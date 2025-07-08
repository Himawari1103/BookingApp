package com.nquang.bookingapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nquang.bookingapp.model.UserModel
import com.nquang.bookingapp.utils.FirebaseUtils
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val user = mutableStateOf<FirebaseUser?>(null)
    var userModel = mutableStateOf<UserModel?>(null)

    init {
        fetchUserData()
    }

    fun fetchUserData() {
        viewModelScope.launch {
            try {
                val currentUser = FirebaseAuth.getInstance().currentUser
                user.value = currentUser
                if(currentUser != null){
                    userModel.value = FirebaseUtils.findUserByUid(user.value!!.uid)
                } else {
                    Log.d("UserViewModel", "User is null")
                }
                Log.d("UserViewModel", "Fetched user data: Email=${userModel.value!!.email}, Name=${userModel.value!!.fullName}")
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching user data: ${e.message}")
            }
        }
    }

    fun refreshUserData() {
        fetchUserData()
    }

    fun updateNickName(nickname: String) {
        userModel.value = userModel.value?.copy(nickname = nickname)
    }

    fun updatePhoneNumber(phoneNumber: String) {
        userModel.value = userModel.value?.copy(phoneNumber = phoneNumber)
    }

    fun updateEmail(email: String) {
        userModel.value = userModel.value?.copy(email = email)
    }

    fun updateGender(gender: String) {
        userModel.value = userModel.value?.copy(gender = gender)
    }

    fun updateBirthDate(birthDate: String) {
        userModel.value = userModel.value?.copy(birthDate = birthDate)
    }

    fun updateAvatarUrl(avatarUrl: String) {
        userModel.value = userModel.value?.copy(avatarUrl = avatarUrl)
    }

    fun updateAddress(address: String) {
        userModel.value = userModel.value?.copy(address = address)
    }
}