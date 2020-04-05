package com.serdnito.pokeapi.domain.model

class NamedResourceList(
    val list: List<NamedResource>
) {

    fun isEmpty() = list.isEmpty()

}