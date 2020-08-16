package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

import androidx.room.TypeConverters
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters.StringArrayListTypeConverters

data class PokemonCardAttacks(
    @TypeConverters(StringArrayListTypeConverters::class)
    protected val cost: ArrayList<String>,
    protected val name: String,
    protected val text: String,
    protected val damage: String,
    protected val convertedEnergyCost: Int) {

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
    /*
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



 */
}