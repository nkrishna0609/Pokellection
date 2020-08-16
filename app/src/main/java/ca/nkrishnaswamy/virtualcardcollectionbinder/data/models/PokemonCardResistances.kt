package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

data class PokemonCardResistances(
    protected val type: String,
    protected val value: String) {

    fun getResistanceType():String{
        return type
    }

    fun getPokemonResistanceAmount():String{
        return value
    }
/*
    override fun toString(): String {
        return "$type - $value"
    }
 */
}