package com.ad_coding.firebasetutorial.ui.screens.cars

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ad_coding.firebasetutorial.domain.model.Car
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CarListViewModel : ViewModel() {

    private var _carList = MutableStateFlow<List<Car>>(emptyList())
    var carList = _carList.asStateFlow()

    private val db = Firebase.firestore

    init {
        getCarList()
    }

    private fun getCarList() {

        db.collection("cars")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {
                    _carList.value = value.toObjects()
                }
            }
    }

    fun createCar() {
        val car = hashMapOf(
            "id" to 2,
            "brand" to "Mitsubishi"
        )

        db.collection("cars")
            .add(car)
            .addOnSuccessListener {
                Log.d("document", "CREATED")
            }
    }

    fun updateCar() {
        val car = hashMapOf(
            "id" to 4,
            "brand" to "Mazda"
        )
        db.collection("cars")
            .document("uQ9C9PzGXxw1ivXm0zZ4")
            .set(car)
            .addOnSuccessListener {
                Log.d("document", "UPDATED")
            }
    }
}