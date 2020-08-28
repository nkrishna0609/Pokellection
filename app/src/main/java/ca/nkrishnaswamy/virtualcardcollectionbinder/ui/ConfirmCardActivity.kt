package ca.nkrishnaswamy.virtualcardcollectionbinder.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.nkrishnaswamy.virtualcardcollectionbinder.R
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard

private lateinit var recyclerView: RecyclerView
private lateinit var adapter: PokemonCardRecyclerAdapter

class ConfirmCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_card)

        val intent: Intent = intent
        val cardOptions = intent.getParcelableArrayListExtra<PokemonCard>("cardsList")

        recyclerView = findViewById<RecyclerView>(R.id.userSelectCard_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PokemonCardRecyclerAdapter(applicationContext)
        recyclerView.adapter = adapter
        cardOptions?.let { adapter.setCardList(it) }

        val cancelButton = findViewById<Button>(R.id.buttonCancel2)
        cancelButton.setOnClickListener{
            finish()
        }
    }
}