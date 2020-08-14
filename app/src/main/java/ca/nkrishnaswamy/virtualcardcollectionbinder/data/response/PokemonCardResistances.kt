package ca.nkrishnaswamy.virtualcardcollectionbinder.data.response

data class PokemonCardResistances(private val type: String, private val value: String) {
    fun getResistanceType():String{
        return type
    }

    fun getPokemonResistanceAmount():String{
        return value
    }

    override fun toString(): String {
        return "$type - $value"
    }
}