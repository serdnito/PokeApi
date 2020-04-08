package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serdnito.pokeapi.data.database.entity.PokemonAbilityEntity
import com.serdnito.pokeapi.data.database.entity.PokemonJoinAbilityEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PokemonJoinAbilityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(pokemonJoinAbility: List<PokemonJoinAbilityEntity>): Completable

    @Query(
        """
        SELECT pokemon_ability_join.ability_id, pokemon_ability_join.is_hidden, abilities.name
        FROM abilities
        INNER JOIN pokemon_ability_join
        ON pokemon_ability_join.ability_id = abilities.id
        WHERE pokemon_ability_join.pokemon_id = :pokemonId
        """
    )
    fun getAbilitiesForPokemon(pokemonId: Int): Single<List<PokemonAbilityEntity>>

}
