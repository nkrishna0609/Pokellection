package ca.nkrishnaswamy.virtualcardcollectionbinder.data.response

data class PokemonCardAncientTrait(private val name: String,private val text: String) {
    fun getPokemonAncientTraitName():String{
        return name
    }

    fun getPokemonAncientTraitText():String{
        return text
    }

}