package ca.nkrishnaswamy.virtualcardcollectionbinder

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    internal var pokemonDbHelper=CompletePokemonCardDatabaseOperations(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jsonParsePokemon("https://api.pokemontcg.io/v1/cards?page=", "{\"cards\":[]}")
    }

    fun jsonParsePokemon(url: String, endBody: String){
        Thread {
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
            val res=pokemonDbHelper.showData
            //println("There are ${res.count} cards in the complete Pokemon Card database.")


        }.start()
    }
}