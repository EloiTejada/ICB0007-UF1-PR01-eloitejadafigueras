package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val rocketList: List<Rocket>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRocketName: TextView = itemView.findViewById(R.id.tv_rocket_name)
        val tvRocketType: TextView = itemView.findViewById(R.id.tv_rocket_type)
        val tvRocketActive: TextView = itemView.findViewById(R.id.tv_rocket_active)
        val tvRocketCostPerLaunch: TextView = itemView.findViewById(R.id.tv_rocket_cost_per_launch)
        val tvRocketSuccessRatePct: TextView = itemView.findViewById(R.id.tv_rocket_success_rate_pct)
        val tvRocketCountry: TextView = itemView.findViewById(R.id.tv_rocket_country)
        val tvRocketCompany: TextView = itemView.findViewById(R.id.tv_rocket_company)
        val tvRocketWikipedia: TextView = itemView.findViewById(R.id.tv_rocket_wikipedia)
        val tvRocketDescription: TextView = itemView.findViewById(R.id.tv_rocket_description)
        val tvRocketHeightMeters: TextView = itemView.findViewById(R.id.tv_rocket_height_meters)
        val tvRocketDiameterMeters: TextView = itemView.findViewById(R.id.tv_rocket_diameter_meters)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = rocketList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rocket = rocketList[position]

        holder.tvRocketName.text = rocket.name
        holder.tvRocketType.text = "Type: ${rocket.type}"
        holder.tvRocketActive.text = if (rocket.active) "Active" else "Inactive"
        holder.tvRocketCostPerLaunch.text = "Cost per Launch: $${rocket.cost_per_launch}"
        holder.tvRocketSuccessRatePct.text = "Success Rate: ${rocket.success_rate_pct}%"
        holder.tvRocketCountry.text = "Country: ${rocket.country}"
        holder.tvRocketCompany.text = "Company: ${rocket.company}"
        holder.tvRocketWikipedia.text = "Wikipedia: ${rocket.wikipedia}"
        holder.tvRocketDescription.text = "Description: ${rocket.description}"
        holder.tvRocketHeightMeters.text = "Height: ${rocket.height.meters}m (${rocket.height.feet} ft)"
        holder.tvRocketDiameterMeters.text = "Diameter: ${rocket.diameter.meters}m (${rocket.diameter.feet} ft)"
    }
}
