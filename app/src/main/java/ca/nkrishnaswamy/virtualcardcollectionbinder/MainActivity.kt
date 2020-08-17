package ca.nkrishnaswamy.virtualcardcollectionbinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.nkrishnaswamy.virtualcardcollectionbinder.network.ApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(IO).launch {
            start()
        }
    }

    private suspend fun start(){
        //pokemonDbHelper.resetDb()
        //addCardPokemon("super rod","", "","", "","")
        //addCardPokemon("", "", "holon phantoms", "8", "", "")
        //addCardPokemon("joltik", "", "black and white", "45", "", "")

    }
    fun ampersandRemover(name: String ): String{
        var changed: String=name
        if (changed.contains(" and ")){
            changed = changed.replace(" and "," \u0026 ")
        }
        return changed
    }
    private suspend fun addCardPokemon(name: String,hp: String,pokeCardSetName: String, pokeCardNumber: String,superType: String, subType: String){
        var cardName: String = ampersandRemover(name)
        var setName: String = ampersandRemover(pokeCardSetName)
        val apiService= ApiService()
        val pokeCardPage = apiService.getCardPage(cardName, hp, setName, pokeCardNumber, superType, subType).await()
    }
}