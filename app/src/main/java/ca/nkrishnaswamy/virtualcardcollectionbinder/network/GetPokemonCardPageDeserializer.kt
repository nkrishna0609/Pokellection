package ca.nkrishnaswamy.virtualcardcollectionbinder.network

import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.*
import ca.nkrishnaswamy.virtualcardcollectionbinder.network.response.PokemonCardPageResponse
import com.google.gson.*
import java.lang.reflect.Type

class GetPokemonCardPageDeserializer: JsonDeserializer<PokemonCardPageResponse>{
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): PokemonCardPageResponse {
        val cardList = arrayListOf<PokemonCard>()
        val jsonCard = json?.asJsonObject

        val jsonArrayCard: JsonArray? = jsonCard?.get("cards")?.asJsonArray

        if (jsonArrayCard != null) {
            for (i in 0 until jsonArrayCard.size()) {
                val jsonCardDetails: JsonObject? = jsonArrayCard.get(i).asJsonObject
                val name = jsonCardDetails?.get("name")?.asString
                val image = jsonCardDetails?.get("imageUrlHiRes")?.asString
                var typesList = arrayListOf<String>()                               //not applicable to every card
                if (jsonCardDetails!!.has("types")) {
                    val typesDetails = jsonCardDetails.get("types").asJsonArray
                    typesList = ArrayList<String>()
                    for (x in 0 until typesDetails.size()) {
                        typesList.add(typesDetails.get(x).asString)
                    }
                }
                val supertype = jsonCardDetails.get("supertype").asString
                val subtype = jsonCardDetails.get("subtype").asString
                var evolvesFrom = ""                                                  //not applicable to every card
                if (jsonCardDetails.has("evolvesFrom")) {
                    evolvesFrom = jsonCardDetails.get("evolvesFrom").asString
                }
                var hp = ""                                                           //not applicable to every card
                if (jsonCardDetails.has("hp")) {
                    hp = jsonCardDetails.get("hp").asString
                }
                val setNum = jsonCardDetails.get("number").asString
                var rarity = ""                                                     //not applicable to every card
                if (jsonCardDetails.has("rarity")){
                    rarity = jsonCardDetails.get("rarity").asString
                }
                val series = jsonCardDetails.get("series").asString
                val setName = jsonCardDetails.get("set").asString
                val setCode = jsonCardDetails.get("setCode").asString
                var attacksList = arrayListOf<PokemonCardAttacks>()                 //not applicable to every card
                if (jsonCardDetails.has("attacks")) {
                    val attacks = jsonCardDetails.get("attacks").asJsonArray
                    attacksList = ArrayList<PokemonCardAttacks>()
                    for (a in 0 until attacks.size()) {
                        val jsonAttackDetails: JsonObject? = attacks.get(a).asJsonObject
                        val name = jsonAttackDetails?.get("name")?.asString
                        var text =""
                        if (jsonAttackDetails?.has("text")!!){
                            text = jsonAttackDetails.get("text")?.asString!!
                        }
                        val damage = jsonAttackDetails.get("damage")?.asString
                        val intEnergyCost = jsonAttackDetails.get("convertedEnergyCost")?.asInt
                        val energyCost = jsonAttackDetails.get("cost")?.asJsonArray
                        val listCost = ArrayList<String>()
                        for (x in 0 until energyCost!!.size()) {
                            listCost.add(energyCost.get(x).asString)
                        }
                        val thisAttack =
                            PokemonCardAttacks(
                                listCost,
                                name!!,
                                text,
                                damage!!,
                                intEnergyCost!!
                            )
                        attacksList.add(thisAttack)
                    }
                }
                var weaknessesList = arrayListOf<PokemonCardWeaknesses>()             //not applicable to every card
                if (jsonCardDetails.has("weaknesses")) {
                    val weaknesses = jsonCardDetails.get("weaknesses").asJsonArray
                    weaknessesList = ArrayList<PokemonCardWeaknesses>()
                    for (w in (0 until weaknesses.size())) {
                        val jsonWeaknessDetails: JsonObject? = weaknesses.get(w).asJsonObject
                        val type = jsonWeaknessDetails?.get("type")?.asString
                        val value = jsonWeaknessDetails?.get("value")?.asString
                        val thisWeakness =
                            PokemonCardWeaknesses(
                                type!!,
                                value!!
                            )
                        weaknessesList.add(thisWeakness)
                    }
                }
                var resistancesList = arrayListOf<PokemonCardResistances>()          //not applicable to every card
                if (jsonCardDetails.has("resistances")) {
                    val resistances = jsonCardDetails.get("resistances").asJsonArray
                    resistancesList = ArrayList<PokemonCardResistances>()
                    for (r in (0 until resistances.size())) {
                        val jsonResistanceDetails: JsonObject? = resistances.get(r).asJsonObject
                        val type = jsonResistanceDetails?.get("type")?.asString
                        val value = jsonResistanceDetails?.get("value")?.asString
                        val thisResistance =
                            PokemonCardResistances(
                                type!!,
                                value!!
                            )
                        resistancesList.add(thisResistance)
                    }
                }
                var ancientTraitName = ""
                var ancientTraitText = ""
                if (jsonCardDetails.has("ancientTrait")) {                     //not applicable to every word
                    val ancientTraitDetails = jsonCardDetails.get("ancientTrait").asJsonObject
                    ancientTraitName = ancientTraitDetails.get("name").asString
                    ancientTraitText = ancientTraitDetails.get("text").asString

                }
                val ancienttrait =
                    PokemonCardAncientTrait(
                        ancientTraitName,
                        ancientTraitText
                    )

                var abilityName = ""
                var abilityText = ""
                var abilityType = ""
                if (jsonCardDetails.has("ability")) {                          //not applicable to every word
                    val abilityDetails = jsonCardDetails.get("ability").asJsonObject
                    abilityName = abilityDetails.get("name").asString
                    abilityText = abilityDetails.get("text").asString
                    if (abilityDetails.has("type")){
                        abilityType = abilityDetails.get("type").asString
                    }
                }
                val ability =
                    PokemonCardAbility(
                        abilityName,
                        abilityText,
                        abilityType
                    )

                val newCard =
                    PokemonCard(
                        name!!,
                        image!!,
                        typesList,
                        supertype,
                        subtype,
                        evolvesFrom,
                        hp,
                        setNum,
                        rarity,
                        series,
                        setName,
                        setCode,
                        attacksList,
                        weaknessesList,
                        resistancesList,
                        ancienttrait,
                        ability
                    )

                cardList.add(newCard)
            }
        }

        return PokemonCardPageResponse(
            cardList
        )
    }
}