package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import com.serdnito.pokeapi.domain.model.PokemonAbility

class PokemonAbilityEntity(
    @ColumnInfo(name = "ability_id") val abilityId: Int,
    @ColumnInfo(name = "is_hidden") val isHidden: Boolean,
    @ColumnInfo(name = "name") val name: String
) {

    fun mapToDomain() = PokemonAbility(abilityId, isHidden, name)

}