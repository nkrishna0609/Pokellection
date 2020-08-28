package ca.nkrishnaswamy.virtualcardcollectionbinder.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.nkrishnaswamy.virtualcardcollectionbinder.R
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard

private lateinit var recyclerView: RecyclerView
private lateinit var adapter: ConfirmCardRecyclerAdapter
private lateinit var cardOptions: ArrayList<PokemonCard>

class ConfirmCardActivity : AppCompatActivity(), ConfirmCardRecyclerAdapter.OnItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_card)

        val intentReceiveCardOptionsList: Intent = intent
        cardOptions = intentReceiveCardOptionsList.getParcelableArrayListExtra<PokemonCard>("cardsList") as ArrayList<PokemonCard>

        recyclerView = findViewById<RecyclerView>(R.id.userSelectCard_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ConfirmCardRecyclerAdapter(applicationContext, this)
        recyclerView.adapter = adapter
        cardOptions.let { adapter.setCardList(it) }

        val cancelButton = findViewById<Button>(R.id.buttonCancel2)
        cancelButton.setOnClickListener{
            finish()
        }
    }

    override fun onItemClick(card: PokemonCard) {
        val intentSendClicked: Intent = intent
        intentSendClicked.putExtra("userSelectedCard",card)
        setResult(Activity.RESULT_OK,intentSendClicked)
        finish()
    }
}