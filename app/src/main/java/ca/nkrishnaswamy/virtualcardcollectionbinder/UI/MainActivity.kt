package ca.nkrishnaswamy.virtualcardcollectionbinder.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import ca.nkrishnaswamy.virtualcardcollectionbinder.R
import ca.nkrishnaswamy.virtualcardcollectionbinder.network.NoConnectionException
import ca.nkrishnaswamy.virtualcardcollectionbinder.viewModels.UserCardsViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserCardsViewModel

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
        addCardPokemon("", "", "holon phantoms", "8", "", "")
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

        viewModel = ViewModelProvider(this).get(UserCardsViewModel::class.java)
        try {
            val cardsList = viewModel.retrieveCardsFromApi(
                cardName,
                hp,
                setName,
                pokeCardNumber,
                superType,
                subType
            )

            for (x in cardsList) {
                println(x.toString())
            }
        } catch (e: NoConnectionException) {
            Log.e("Internet Status","No Internet Connection.")
        }
    }
}