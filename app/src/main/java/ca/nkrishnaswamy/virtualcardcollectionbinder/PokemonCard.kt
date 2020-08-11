package ca.nkrishnaswamy.virtualcardcollectionbinder

import com.google.gson.Gson

class PokemonCard (private val id: String, private val name: String, private val nationalPokedexNumber: Int,private val imageUrlHiRes: String,private val types: ArrayList<String> ,private val supertype: String,private val subtype: String,private val evolvesFrom: String,private val hp: String,private val retreatCost: ArrayList<String> ,private val number: String, private val rarity: String,private val series: String,private val set: String,private val setCode: String,private val attacks: ArrayList<PokemonCardAttacks>, private val weaknesses: ArrayList<PokemonCardWeaknesses>, private val resistances: ArrayList<PokemonCardResistances>, private val ancientTrait: PokemonCardAncientTrait,private val ability: PokemonCardAbility) {

    fun pokemonTypesSerialized(): String{
        val gson = Gson()
        val toStoreTypes: String = gson.toJson(types)
        return toStoreTypes
    }

    fun pokemonRetreatCostSerialized(): String{
        val gson = Gson()
        val toStoreTypes: String = gson.toJson(retreatCost)
        return toStoreTypes
    }

    fun getRetreatCost():ArrayList<String>{
        return retreatCost
    }

    fun getAbility(): PokemonCardAbility{
        return ability
    }

    fun getAncientTrait(): PokemonCardAncientTrait{
        return ancientTrait
    }
    fun getPokemonTypes():ArrayList<String>{
        return types
    }

    fun getAttacks():ArrayList<PokemonCardAttacks>{
        return attacks
    }

    fun getWeaknesses():ArrayList<PokemonCardWeaknesses>{
        return weaknesses
    }

    fun getResistances():ArrayList<PokemonCardResistances>{
        return resistances
    }

    fun getId():String{
        return id
    }

    fun getName():String{
        return name
    }

    fun getPokedexNum():Int{
        return nationalPokedexNumber
    }

    fun getImage():String{
        return imageUrlHiRes
    }

    fun getSuperType():String{
        return supertype
    }

    fun getSubType():String{
        return subtype
    }

    fun getEvolvesFrom():String{
        return evolvesFrom
    }

    fun gethp():String{
        return hp
    }

    fun getSetNum():String{
        return number
    }

    fun getRarity():String{
        return rarity
    }
    fun getSeriesName():String{
        return series
    }

    fun getSetName():String{
        return set
    }

    fun getSetCode():String{
        return setCode
    }

    override fun toString():String{
        return "$name - $set $number"
    }
}