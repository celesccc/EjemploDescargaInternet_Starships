package com.cbellmont.ejemplodescargainternet

import com.google.gson.annotations.SerializedName

data class Starships(
    var MGLT: String,
    @SerializedName("cargo_capacity")var cargoCapacity : String,
    var consumables: String,
    @SerializedName("cost_in_credits")var costInCredits: String,
    var created: String,
    var crew: String,
    var edited: String,
    @SerializedName("hyperdrive_rating")var hyperdriveRating: String,
    var length: String,
    var manufacturer: String,
    @SerializedName("max_atmosphering_speed")var maxAtmospheringSpeed: String,
    var model: String,
    var name: String,
    var passengers: String,
    var films: List<String>?,
    var pilots: List<String>,
    @SerializedName("starship_class")var starshipClass: String,
    var url: String ) {

    override fun toString(): String {
        return "$MGLT, $created\n"
    }
}

