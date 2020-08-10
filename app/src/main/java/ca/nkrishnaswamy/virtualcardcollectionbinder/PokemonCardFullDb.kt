package ca.nkrishnaswamy.virtualcardcollectionbinder

object PokemonCardFullDb {
    private var pokemonCardDb: ArrayList<PokemonCardPage> = arrayListOf<PokemonCardPage>()  //singleton

    fun getPokemonDb(): ArrayList<PokemonCardPage>{
        return pokemonCardDb
    }

}