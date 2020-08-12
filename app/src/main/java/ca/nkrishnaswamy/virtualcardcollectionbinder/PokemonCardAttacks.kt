package ca.nkrishnaswamy.virtualcardcollectionbinder

class PokemonCardAttacks(private val cost: ArrayList<String>, private val name: String, private val text: String, private val damage: String, private val convertedEnergyCost: Int) {
    fun getAttackCosts():ArrayList<String>{
        return cost
    }

    override fun toString():String{
        return "\t\t\tName: $name\n\t\t\tDescription: $text\n\t\t\tDamage: $damage\n\t\t\tCost: ${costToString()}"
    }

    fun costToString():String{
        var output=""
        var separator = ""

        for (cost in cost){
            output=output+separator + cost
            separator = ", "
        }

        return output
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