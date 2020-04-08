package com.serdnito.pokeapi.data.api.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import com.serdnito.pokeapi.domain.model.Species

class Species(
    @SerializedName("color")
    val color: NamedApiResource,
    @SerializedName("egg_groups")
    val eggGroups: List<NamedApiResource>,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorText>,
    @SerializedName("genera")
    val genus: List<Genus>,
    @SerializedName("generation")
    val generation: NamedApiResource,
    @SerializedName("id")
    val id: Int,
    @SerializedName("shape")
    val shape: NamedApiResource
) {

    @SuppressLint("DefaultLocale")
    fun mapToDomain() =
        Species(
            color.name.capitalize(),
            eggGroups.map { it.name.decapitalize() },
            flavorTextEntries.filter { it.language.name == "en" }.map { it.mapToDomain() },
            genus.find { it.language.name == "en" }?.genus ?: "",
            generation.name.split("-").joinToString(" ") { it.capitalize() },
            id,
            shape.name.capitalize()
        )

}