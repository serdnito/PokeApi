package com.serdnito.pokeapi.domain.model

enum class Stat(val id: Int) {
    None(0),
    Hp(1),
    Attack(2),
    Defense(3),
    SpecialAttack(4),
    SpecialDefense(5),
    Speed(6);

    companion object {
        fun getById(id: Int) = values().find { it.id == id } ?: None
    }

}