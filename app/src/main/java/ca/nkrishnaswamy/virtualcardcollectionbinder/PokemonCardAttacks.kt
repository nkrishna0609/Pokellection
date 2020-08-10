package ca.nkrishnaswamy.virtualcardcollectionbinder

class PokemonCardAttacks(private val cost: ArrayList<String>, private val name: String, private val text: String, private val damage: String, private val convertedEnergyCost: Int) {
    fun getAttackCosts():ArrayList<String>{
        return cost
    }

    fun getPokemonAttackName():String{
        return name
    }

    fun getPokemonAttackText():String{
        return text
    }

    fun getPokemonAttackDamage():String{
        return damage
    }
    fun getPokemonAttacEnergyCost():Int{
        return convertedEnergyCost
    }
}