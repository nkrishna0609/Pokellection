package ca.nkrishnaswamy.virtualcardcollectionbinder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.nkrishnaswamy.virtualcardcollectionbinder.*
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.ApiService
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.UserPokemonCardDatabaseOperations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    internal var pokemonDbHelper=
        UserPokemonCardDatabaseOperations(
            this
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start()
    }

    fun start(){
        //pokemonDbHelper.resetDb()
        addCardPokemon("super rod","", "","", "","")
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
        var cardName: String = ampersandRemover(name)
        var setName: String = ampersandRemover(pokeCardSetName)
        val apiService=ApiService()
        GlobalScope.launch(Dispatchers.IO) {
            val pokeCardPage = apiService.getCardPage(cardName, hp, setName, pokeCardNumber, superType, subType).await()
            for (card in pokeCardPage.getPokemonCardPages()){
                println(card.toString())
            }
        }
    }
}