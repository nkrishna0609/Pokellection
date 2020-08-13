package ca.nkrishnaswamy.virtualcardcollectionbinder

class PokemonCardAbility (private val name: String, private val text: String,private val type: String){

    fun getPokemonAbilityName():String{
            return name
    }

    fun getPokemonAbilityText():String?{
        return text
    }

    fun getPokemonAbilityType():String?{
        return type
    }
}