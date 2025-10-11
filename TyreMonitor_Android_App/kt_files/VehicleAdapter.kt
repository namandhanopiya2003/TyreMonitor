package com.example.tyremonitor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Shows a list of vehicles in RecyclerView
class VehicleAdapter(private val context: Context, private val vehicles: List<Vehicle>) :
    RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    // It holds the views for each item in the list    
    inner class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvVehicleName)
        val tvReg: TextView = itemView.findViewById(R.id.tvVehicleReg)
    }

    // This function creates the layout for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehicle, parent, false)
        return VehicleViewHolder(view)
    }

    // It sets the data for each item in the list
    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicles[position]

        // Shows the vehicle's name and registration number in the TextViews
        holder.tvName.text = vehicle.name
        holder.tvReg.text = vehicle.regNo

        // When the user clicks on a vehicle item, it goes to the details screen
        holder.itemView.setOnClickListener {
            val intent = Intent(context, VehicleDetailsActivity::class.java)
            intent.putExtra("vehicle", vehicle)
            context.startActivity(intent)
        }
    }

    // It tells RecyclerView how many items are in the list
    override fun getItemCount(): Int = vehicles.size
}

