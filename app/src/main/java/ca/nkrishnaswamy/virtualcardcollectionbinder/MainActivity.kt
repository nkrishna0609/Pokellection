package ca.nkrishnaswamy.virtualcardcollectionbinder

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.GsonBuilder
import okhttp3.*
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
        addCardPokemon("","", "holon phantoms","8", "","")
        addCardPokemon("","","xy black star promos","xy60","","")

        //deleteCardPokemon("charizard","120", "legendary power","3", "pokemon","stage 2")
    }

    fun addCardPokemon(name: String,hp: String,pokeCardSetName: String, pokeCardNumber: String,superType: String, subType: String){
        Thread{
            var url = "https://api.pokemontcg.io/v1/cards?"

            val client = OkHttpClient()
            try {
                    val request = Request.Builder().url("${url}name=${name}&&hp=${hp}&&set=${pokeCardSetName}&&number=${pokeCardNumber}&&supertype=${superType}&&subtype=${subType}").build()

                    client.newBuilder().build()
                    client.newCall(request).execute().use { response ->
                            val body = response.body?.string()

                            val gson = GsonBuilder().create()

                            val pokemonPage = gson.fromJson(body, PokemonCardPage::class.java)
                            for (card in pokemonPage.getPokemonCardPages()){
                                pokemonDbHelper.insertCard(card)
                            }

                            println("There are ${pokemonDbHelper.showData.count} cards in the complete Pokemon Card database.")
                            val res=pokemonDbHelper.showData
                            val buffer=StringBuffer()
                            while (res.moveToNext()){
                                buffer.append("ID: "+res.getString(0)+"\n")
                                buffer.append("Name: "+res.getString(1)+"\n")
                                buffer.append("HP: "+res.getString(9)+"\n")
                                buffer.append("Type(s): "+res.getString(5)+"\n")
                                buffer.append("Attack(s): "+res.getString(16)+"\n")
                                buffer.append("Ability: "+res.getString(20)+"\n")
                                buffer.append("Weakness(es): "+res.getString(17)+"\n")
                                buffer.append("Resistance(s): "+res.getString(18)+"\n")
                                buffer.append("Retreat Cost: "+res.getString(10)+"\n")
                                buffer.append("Ancient Trait(s): "+res.getString(19)+"\n")
                                buffer.append("Set Name: "+res.getString(14)+"\n")
                                buffer.append("Set Number: "+res.getString(11)+"\n")
                                buffer.append("Super Type: "+res.getString(6)+"\n")
                                buffer.append("Sub Type: "+res.getString(7)+"\n")
                            }
                            println(buffer.toString())

                    }


            } catch (e: IOException) {
                println("Parsing failed: $e")
            }


        }.start()
    }
}