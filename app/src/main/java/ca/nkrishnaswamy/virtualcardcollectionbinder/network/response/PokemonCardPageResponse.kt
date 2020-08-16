package ca.nkrishnaswamy.virtualcardcollectionbinder.network.response

import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard

data class PokemonCardPageResponse(private val cards: ArrayList<PokemonCard>){
    fun getPokemonCards():ArrayList<PokemonCard>{
        return cards
    }
}