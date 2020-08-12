package ca.nkrishnaswamy.virtualcardcollectionbinder

class PokemonCardWeaknesses (private val type: String, private val value: String) {
    fun getWeaknessType():String{
        return type
    }

    fun getPokemonWeaknessAmount():String{
        return value
    }

    override fun toString(): String {
        return "$type - $value"
    }
}