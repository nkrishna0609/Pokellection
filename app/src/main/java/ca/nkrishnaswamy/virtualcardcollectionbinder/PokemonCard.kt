package ca.nkrishnaswamy.virtualcardcollectionbinder

class PokemonCard (private val id: String, private val name: String, private val nationalPokedexNumber: Int,private val imageUrlHiRes: String,private val types: ArrayList<String> ,private val supertype: String,private val subtype: String,private val evolvesFrom: String,private val hp: String,private val retreatCost: ArrayList<String> ,private val number: String, private val rarity: String,private val series: String,private val set: String,private val setCode: String,private val attacks: ArrayList<PokemonCardAttacks>, private val weaknesses: ArrayList<PokemonCardWeaknesses>) {
    fun getRetreatCost():ArrayList<String>{
        return retreatCost
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