package com.example.tyremonitor

// Data class that stores information about vehicle and its tyres
data class Vehicle(
    val name: String,
    val regNo: String,
    val tyrePressure: Double,
    val tyreAge: String,
    val loadCapacity: String
) : java.io.Serializable              // This enables to send this Vehicle object between screens (activities)
