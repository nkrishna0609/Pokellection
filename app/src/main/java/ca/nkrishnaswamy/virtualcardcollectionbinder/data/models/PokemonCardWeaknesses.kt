package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

data class PokemonCardWeaknesses (
    protected val type: String,
    protected val value: String) {

    fun getWeaknessType():String{
        return type
    }

    fun getPokemonWeaknessAmount():String{
        return value
    }
}