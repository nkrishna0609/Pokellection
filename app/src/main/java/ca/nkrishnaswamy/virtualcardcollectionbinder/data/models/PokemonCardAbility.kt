package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

data class PokemonCardAbility (
    protected val name: String,
    protected val text: String,
    protected val type: String){

    fun getAbilityName(): String{
        return name
    }

    fun getAbilityText(): String{
        return text
    }

    fun getAbilityType(): String{
        return type
    }
}