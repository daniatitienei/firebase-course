package com.ad_coding.firebasetutorial.ui.screens.car

import androidx.lifecycle.ViewModel
import com.ad_coding.firebasetutorial.domain.model.Car
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CarViewModel : ViewModel() {

    private var _car = MutableStateFlow<Car?>(null)
    var car = _car.asStateFlow()

    init {
        getCarByID()
    }

    fun getCarByID() {
        val db = Firebase.firestore

        db.collection("cars")
            .document("uQ9C9PzGXxw1ivXm0zZ4")
            .get()
            .addOnSuccessListener { documentSnapshot ->
                _car.value = documentSnapshot.toObject()
            }
    }
}