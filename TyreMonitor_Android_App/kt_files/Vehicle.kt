package com.example.tyremonitor

data class Vehicle(
    val name: String,
    val regNo: String,
    val tyrePressure: Double,
    val tyreAge: String,
    val loadCapacity: String
) : java.io.Serializable
