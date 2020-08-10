package ca.nkrishnaswamy.virtualcardcollectionbinder

class PokemonCardPage(private val cards: ArrayList<PokemonCard>){
    fun getPokemonCardList():ArrayList<PokemonCard>{
        return cards
    }
}