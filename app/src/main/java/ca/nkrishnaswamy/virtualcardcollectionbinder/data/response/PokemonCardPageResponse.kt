package ca.nkrishnaswamy.virtualcardcollectionbinder.data.response

data class PokemonCardPageResponse(private val cards: ArrayList<PokemonCard>){
    fun getPokemonCardPages():ArrayList<PokemonCard>{
        return cards
    }
}