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

    internal var pokemonDbHelper=CompletePokemonCardDatabaseOperations(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start()
    }

    fun start(){
            if (pokemonDbHelper.showData.count==0){         //this means that the full JSON db will populate for the first time after downloading the app
                jsonParserPokemon("https://api.pokemontcg.io/v1/cards?page=", "{\"cards\":[]}")
            }
            if (pokemonDbHelper.showData.count!=0) {        //this means that the full JSON db will update once the user clicks the "update JSON db" button
                val buttonUpdatePokeJSONdb = findViewById<Button>(R.id.buttonUpdatePokeJSONdb)
                buttonUpdatePokeJSONdb.setOnClickListener{
                    try{
                            pokemonDbHelper.resetDb()
                            jsonParserPokemon(
                                "https://api.pokemontcg.io/v1/cards?page=",
                                "{\"cards\":[]}"
                            )
                    }
                    catch (e:Exception){
                        e.printStackTrace()
                        Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

    fun jsonParserPokemon(url: String, endBody: String){
        Thread{
            var pageNum = 1
            var isEmpty=false

            val client = OkHttpClient()
            try {
                while (!isEmpty) {
                    val request = Request.Builder().url("$url$pageNum").build()

                    client.newBuilder().build()
                    client.newCall(request).execute().use { response ->
                        val body = response.body?.string()

                        if (body == endBody) {
                            isEmpty=true
                            //println("Page $pageNum is empty")
                        }
                        else {
                            val gson = GsonBuilder().create()

                            val pokemonPage = gson.fromJson(body, PokemonCardPage::class.java)
                            for (card in pokemonPage.getPokemonCardPages()){
                                pokemonDbHelper.insertCard(card)
                            }

                            //println("Page Num: $pageNum")
                            //val res=pokemonDbHelper.showData
                            //val buffer=StringBuffer()
                            //while (res.moveToNext()){
                            //buffer.append("ID: "+res.getString(0)+"\n")
                            //}
                            //println(buffer.toString())
                            //println(res.count)
                            pageNum++
                        }
                    }
                }

            } catch (e: IOException) {
                println("Parsing failed: $e")
            }

            //println("There are ${pokemonDbHelper.showData.count} cards in the complete Pokemon Card database.")
        }.start()
    }
}