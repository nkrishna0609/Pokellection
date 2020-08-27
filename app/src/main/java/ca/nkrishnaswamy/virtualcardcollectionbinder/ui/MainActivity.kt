package ca.nkrishnaswamy.virtualcardcollectionbinder.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.nkrishnaswamy.virtualcardcollectionbinder.R
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard
import ca.nkrishnaswamy.virtualcardcollectionbinder.network.NoConnectionException
import ca.nkrishnaswamy.virtualcardcollectionbinder.viewModels.UserCardsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserCardsViewModel
    private val newCardActivityRequestCode = 1
    lateinit private var adapter: PokemonCardRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.pokeCard_recycler_view)
        adapter = PokemonCardRecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(UserCardsViewModel::class.java)

        viewModel.getAllCardsInDb().observe(this, Observer {cards ->
            cards.let{adapter.setCardList(it)}
        })

        val addCardButton = findViewById<FloatingActionButton>(R.id.add_card_button)
        addCardButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewCardActivity::class.java)
            startActivityForResult(intent, newCardActivityRequestCode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val searchCard: MenuItem? = menu?.findItem(R.id.search_item)
        val searchView: SearchView = searchCard?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.deleteAllCards -> {
                CoroutineScope(IO).launch {
                    viewModel.deleteAllCards()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            R.string.deleteAllCardsNotification,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if((requestCode==newCardActivityRequestCode) && (resultCode== Activity.RESULT_OK)){
            val pokeCardName: String = data?.getStringExtra("cardName").toString()
            val cardName: String = ampersandRemover(pokeCardName)
            val pokeCardSetName: String = data?.getStringExtra("cardSetName").toString()
            val cardSetName: String = ampersandRemover(pokeCardSetName)
            val pokeCardSetNum = data?.getStringExtra("cardSetNum").toString()

            CoroutineScope(IO).launch {
                try {
                    val cardsList: List<PokemonCard> = viewModel.retrieveCardsFromApi(cardName, "", cardSetName, pokeCardSetNum)

                    if (cardsList.isEmpty()) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                applicationContext,
                                R.string.errorCardNotFound,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    else {
                        for (x in cardsList) {
                            viewModel.insertCard(x)
                        }
                    }

                } catch (e: NoConnectionException) {
                    //Log.e("Internet Status","No Internet Connection.")
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            R.string.errorNoInternet,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun ampersandRemover(name: String ): String{
        var changed: String=name
        if (changed.contains(" and ")){
            changed = changed.replace(" and "," \u0026 ")
        }
        return changed
    }
}