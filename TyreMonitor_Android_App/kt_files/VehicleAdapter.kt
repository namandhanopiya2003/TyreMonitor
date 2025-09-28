package com.example.tyremonitor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VehicleAdapter(private val context: Context, private val vehicles: List<Vehicle>) :
    RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    inner class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvVehicleName)
        val tvReg: TextView = itemView.findViewById(R.id.tvVehicleReg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehicle, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.tvName.text = vehicle.name
        holder.tvReg.text = vehicle.regNo

        holder.itemView.setOnClickListener {
            val intent = Intent(context, VehicleDetailsActivity::class.java)
            intent.putExtra("vehicle", vehicle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = vehicles.size
}
