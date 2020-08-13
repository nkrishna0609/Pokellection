package ca.nkrishnaswamy.virtualcardcollectionbinder

//import com.google.gson.Gson

class PokemonCard(private val id: String, private val name: String, private val nationalPokedexNumber: Int,private val imageUrlHiRes: String,private val types: ArrayList<String> ,private val supertype: String,private val subtype: String,private val evolvesFrom: String,private val hp: String,private val convertedRetreatCost: Int ,private val number: String, private val rarity: String,private val series: String,private val set: String,private val setCode: String,private val attacks: ArrayList<PokemonCardAttacks>, private val weaknesses: ArrayList<PokemonCardWeaknesses>, private val resistances: ArrayList<PokemonCardResistances>, private val ancientTrait: PokemonCardAncientTrait,private val ability: PokemonCardAbility) {

    //fun pokemonTypesSerialized(): String{
        //val gson = Gson()
        //val toStoreTypes: String = gson.toJson(types)
        //return toStoreTypes
    //}

    fun pokemonWeaknessesToString(): String{
        var output=""
        var separator = ""

        if (weaknesses.isNullOrEmpty()){
            output= "Not applicable to this card."
        }
        else{
            for (weakness in weaknesses){
                output=output+separator + weakness.toString()
                separator = ", "
            }
        }
        return output
    }
    fun pokemonAncientTraitToString(): String {
        if (ancientTrait.getPokemonAncientTraitName().isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return "\n\t\tName: ${ancientTrait.getPokemonAncientTraitName()}\n\t\tDescription: ${ancientTrait.getPokemonAncientTraitText()}"
        }
    }

    fun pokemonAbilityToString(): String {
        if (ability.getPokemonAbilityName().isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return "\n\t\tName: ${ability.getPokemonAbilityName()}\n\t\tType: ${ability.getPokemonAbilityType()}\n\t\tDescription: ${ability.getPokemonAbilityText()}"

        }
    }

    fun pokemonResistancesToString(): String{
        var output=""
        var separator = ""
        if (resistances.isNullOrEmpty()){
            output= "Not applicable to this card."
        }
        else{
            for (resistance in resistances){
                output=output+separator + resistance.toString()
                separator = ", "
            }
        }
        return output
    }

    fun pokemonTypesToString(): String{
        var output=""
        var separator = ""

        if (types.isNullOrEmpty()){
            output= "Not applicable to this card."
        }
        else{
            for (type in types){
                output=output+separator + type
                separator = ", "
            }
        }
        return output
    }

    fun pokemonAttacksToString(): String{
        var output=""
        var count=1

        if (attacks.isNullOrEmpty()){
            output="Not applicable to this card."
        }
        else{
            for (attack in attacks){
                output += "\n\t\tAttack $count: \n${attack.toString()}"
                count++
            }
        }
        return output
    }

    //fun pokemonRetreatCostSerialized(): String{
        //val gson = Gson()
        //val toStoreTypes: String = gson.toJson(retreatCost)
        //return toStoreTypes
    //}

    fun getRetreatCost():String{
        if (convertedRetreatCost==-1){
            return "Not applicable to this card."
        }
        else{
            return convertedRetreatCost.toString()
        }
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

    fun getPokedexNum():String{
        if (nationalPokedexNumber==-1){
            return "Not applicable to this card."
        }
        else{
            return nationalPokedexNumber.toString()
        }
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
        if (evolvesFrom.isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return evolvesFrom
        }
    }

    fun gethp():String{
        if (hp.isNullOrEmpty()){
            return "Not applicable to this card."
        }
        else{
            return hp
        }
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
