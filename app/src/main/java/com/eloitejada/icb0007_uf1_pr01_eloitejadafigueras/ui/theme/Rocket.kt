package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras.ui.theme

import com.google.gson.annotations.SerializedName

data class Height(
    @SerializedName("meters") var meters: Double,
    @SerializedName("feet") var feet: Double
)
data class Diameter(
    @SerializedName("meters") var meters: Double,
    @SerializedName("feet") var feet: Double
)
data class Rocket(@SerializedName("name")var name: String,
                  @SerializedName("type")var type: String,
                  @SerializedName("active")var active: Boolean,
                  @SerializedName("cost_per_launch")var cost_per_launch: Int,
                  @SerializedName("success_rate_pct")var success_rate_pct: Int,
                  @SerializedName("country")var country: String,
                  @SerializedName("company")var company: String,
                  @SerializedName("wikipedia")var wikipedia: String,
                  @SerializedName("description")var description: String,
                  @SerializedName("height")var height: Height,
                  @SerializedName("diameter")var diameter: Diameter)
