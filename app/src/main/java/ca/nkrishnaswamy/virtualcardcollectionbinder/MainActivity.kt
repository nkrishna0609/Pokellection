package ca.nkrishnaswamy.virtualcardcollectionbinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    internal var pokemonDbHelper=UserPokemonCardDatabaseOperations(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start()
    }

    fun start(){
            pokemonDbHelper.resetDb()
            //addCardPokemon("charizard","120", "legendary collection","3", "pokemon","stage 2")
            //addCardPokemon("", "", "holon phantoms", "8", "", "")
            //addCardPokemon("joltik", "", "black and white", "45", "", "")

            //println(pokemonDbHelper.dataToString())

    }
    fun ampersandRemover(name: String ): String{
        var changed: String=name
        if (changed.contains(" and ")){
            changed = changed.replace(" and "," \u0026 ")
        }
        return changed
    }
    fun addCardPokemon(name: String,hp: String,pokeCardSetName: String, pokeCardNumber: String,superType: String, subType: String){
        Thread {                                                                                  //Testing purposes; delete later
            var cardName: String = ampersandRemover(name)
            var setName: String = ampersandRemover(pokeCardSetName)

            var url = "https://api.pokemontcg.io/v1/cards?"

            val client = OkHttpClient()
            try {
                val request = Request.Builder()
                    .url("${url}name=${cardName}&&hp=${hp}&&set=${setName}&&number=${pokeCardNumber}&&supertype=${superType}&&subtype=${subType}")
                    .build()

                client.newBuilder().build()
                client.newCall(request).execute().use { response ->
                    val body = response.body?.string()
                    val jsonCard = JSONObject(body)

                    var jsonArrayCard: JSONArray = jsonCard.getJSONArray("cards")
                    var i = 0

                    for (i in 0..(jsonArrayCard.length() - 1)) {
                        var jsonCardDetails: JSONObject = jsonArrayCard.getJSONObject(i)
                        val name = jsonCardDetails.getString("name")
                        val pokeId = jsonCardDetails.getString("id")
                        var pokedexNum =
                            -1                                                   //not applicable to every card
                        if (jsonCardDetails.has("nationalPokedexNumber")) {
                            pokedexNum = jsonCardDetails.getInt("nationalPokedexNumber")
                        }
                        val image = jsonCardDetails.getString("imageUrlHiRes")
                        var typesList =
                            arrayListOf<String>()                               //not applicable to every card
                        if (jsonCardDetails.has("types")) {
                            val typesDetails = jsonCardDetails.getJSONArray("types")
                            typesList = ArrayList<String>()
                            for (x in 0..(typesDetails.length() - 1)) {
                                typesList.add(typesDetails.getString(x))
                            }
                        }
                        val supertype = jsonCardDetails.getString("supertype")
                        val subtype = jsonCardDetails.getString("subtype")
                        var evolvesFrom =
                            ""                                                  //not applicable to every card
                        if (jsonCardDetails.has("evolvesFrom")) {
                            evolvesFrom = jsonCardDetails.getString("evolvesFrom")
                        }
                        var hp =
                            ""                                                           //not applicable to every card
                        if (jsonCardDetails.has("hp")) {
                            hp = jsonCardDetails.getString("hp")
                        }
                        var retreatCost =
                            -1                                                  //not applicable to every card
                        if (jsonCardDetails.has("convertedRetreatCost")) {
                            retreatCost = jsonCardDetails.getInt("convertedRetreatCost")
                        }
                        val setNum = jsonCardDetails.getString("number")
                        val rarity = jsonCardDetails.getString("rarity")
                        val series = jsonCardDetails.getString("series")
                        val setName = jsonCardDetails.getString("set")
                        val setCode = jsonCardDetails.getString("setCode")
                        var attacksList =
                            arrayListOf<PokemonCardAttacks>()                 //not applicable to every card
                        if (jsonCardDetails.has("attacks")) {
                            val attacks = jsonCardDetails.getJSONArray("attacks")
                            attacksList = ArrayList<PokemonCardAttacks>()
                            for (a in 0..(attacks.length() - 1)) {
                                val jsonAttackDetails: JSONObject = attacks.getJSONObject(a)
                                val name = jsonAttackDetails.getString("name")
                                val text = jsonAttackDetails.getString("text")
                                val damage = jsonAttackDetails.getString("damage")
                                val intEnergyCost = jsonAttackDetails.getInt("convertedEnergyCost")
                                val energyCost = jsonAttackDetails.getJSONArray("cost")
                                var listCost = ArrayList<String>()
                                for (x in 0..(energyCost.length() - 1)) {
                                    listCost.add(energyCost.getString(x))
                                }
                                val thisAttack =
                                    PokemonCardAttacks(listCost, name, text, damage, intEnergyCost)
                                attacksList.add(thisAttack)
                            }
                        }
                        var weaknessesList =
                            arrayListOf<PokemonCardWeaknesses>()             //not applicable to every card
                        if (jsonCardDetails.has("weaknesses")) {
                            val weaknesses = jsonCardDetails.getJSONArray("weaknesses")
                            weaknessesList = ArrayList<PokemonCardWeaknesses>()
                            for (w in (0..weaknesses.length() - 1)) {
                                val jsonWeaknessDetails: JSONObject = weaknesses.getJSONObject(w)
                                val type = jsonWeaknessDetails.getString("type")
                                val value = jsonWeaknessDetails.getString("value")
                                val thisWeakness = PokemonCardWeaknesses(type, value)
                                weaknessesList.add(thisWeakness)
                            }
                        }
                        var resistancesList =
                            arrayListOf<PokemonCardResistances>()          //not applicable to every card
                        if (jsonCardDetails.has("resistances")) {
                            var resistances = jsonCardDetails.getJSONArray("resistances")
                            resistancesList = ArrayList<PokemonCardResistances>()
                            for (r in (0..resistances.length() - 1)) {
                                val jsonResistanceDetails: JSONObject = resistances.getJSONObject(r)
                                val type = jsonResistanceDetails.getString("type")
                                val value = jsonResistanceDetails.getString("value")
                                val thisResistance = PokemonCardResistances(type, value)
                                resistancesList.add(thisResistance)
                            }
                        }
                        var ancientTraitName = ""
                        var ancientTraitText = ""
                        if (jsonCardDetails.has("ancientTrait")) {                     //not applicable to every word
                            var ancientTraitDetails = jsonCardDetails.getJSONObject("ancientTrait")
                            ancientTraitName = ancientTraitDetails.getString("name")
                            ancientTraitText = ancientTraitDetails.getString("text")

                        }
                        var ancienttrait =
                            PokemonCardAncientTrait(ancientTraitName, ancientTraitText)

                        var abilityName = ""
                        var abilityText = ""
                        var abilityType = ""
                        if (jsonCardDetails.has("ability")) {                          //not applicable to every word
                            var abilityDetails = jsonCardDetails.getJSONObject("ability")
                            abilityName = abilityDetails.getString("name")
                            abilityText = abilityDetails.getString("text")
                            abilityType = abilityDetails.getString("type")

                            var ability = PokemonCardAbility(abilityName, abilityText, abilityType)
                        }
                        var ability = PokemonCardAbility(abilityName, abilityText, abilityType)

                        val newCard = PokemonCard(
                            pokeId,
                            name,
                            pokedexNum,
                            image,
                            typesList,
                            supertype,
                            subtype,
                            evolvesFrom,
                            hp,
                            retreatCost,
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
                        pokemonDbHelper.insertCard(newCard)
                    }
                }
            } catch (e: IOException) {
                println("Parsing failed: $e")
            }
        }.start()                                                                                 //Testing purposes; delete later
    }
}