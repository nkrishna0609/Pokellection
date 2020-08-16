package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

data class PokemonCardAncientTrait(
    protected val name: String,
    protected val text: String) {

    fun getTraitName(): String{
        return name
    }

    fun getTraitText(): String{
        return text
    }

}