package ca.nkrishnaswamy.virtualcardcollectionbinder.ui

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.nkrishnaswamy.virtualcardcollectionbinder.R
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard
import ca.nkrishnaswamy.virtualcardcollectionbinder.network.NoConnectionException
import ca.nkrishnaswamy.virtualcardcollectionbinder.viewModels.UserCardsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserCardsViewModel
    private val newCardActivityRequestCode = 1
    private lateinit var adapter: PokemonCardRecyclerAdapter

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

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteCard(adapter.getPokeCardAt(viewHolder.adapterPosition))
                Toast.makeText(applicationContext, R.string.cardDeletedNotif, Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
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
            val cardSetNum: String = enteredSetNumChecker(pokeCardSetNum)

            CoroutineScope(IO).launch {
                try {
                    val cardsList: List<PokemonCard> = viewModel.retrieveCardsFromApi(cardName, "", cardSetName, cardSetNum)

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
                        if (cardsList.size==1) {
                            for (x in cardsList) {
                                viewModel.insertCard(x)
                            }
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

    private fun enteredSetNumChecker(num: String): String {
        var changed: String = num
        if (changed.contains("/")){
            changed = changed.substring(0,changed.indexOf("/"))
        }
        return changed
    }
}