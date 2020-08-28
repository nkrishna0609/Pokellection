package ca.nkrishnaswamy.virtualcardcollectionbinder.data.models

import androidx.room.TypeConverters
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.typeConverters.StringArrayListTypeConverters

data class PokemonCardAttacks(
    @TypeConverters(StringArrayListTypeConverters::class)
    protected val cost: ArrayList<String>,
    protected val name: String,
    protected val text: String,
    protected val damage: String,
    protected val convertedEnergyCost: Int){

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