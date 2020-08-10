package ca.nkrishnaswamy.virtualcardcollectionbinder

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
                            PokemonCardFullDb.getPokemonDb().add(pokemonPage)

                            //println("Page Num: $pageNum")
                            //println(PokemonCardFullDb.getPokemonDb().size)
                            pageNum++
                        }
                    }
                }

            } catch (e: IOException) {
                println("Parsing failed: $e")
            }
            var totalCount=0
            for (page in PokemonCardFullDb.getPokemonDb()){
                totalCount += page.getPokemonCardPages().size
            }
            println("There are $totalCount cards in the complete database.")

            //var name = "charizard"                            //all this code is to test filtering and adding functionalities to the user's collection
            //var hp = "120"
            //UserPokemonCardCollection.pokemonCollectionAdder(name, hp)
            //for (x in UserPokemonCardCollection.getPokemonCollection()){
                //println(x.toString())
            //}
        }.start()
    }
}