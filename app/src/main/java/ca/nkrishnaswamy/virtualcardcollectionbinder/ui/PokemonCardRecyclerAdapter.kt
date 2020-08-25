package ca.nkrishnaswamy.virtualcardcollectionbinder.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ca.nkrishnaswamy.virtualcardcollectionbinder.R
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_poke_card_list.view.*

class PokemonCardRecyclerAdapter internal constructor(context: Context): RecyclerView.Adapter<PokemonCardRecyclerAdapter.PokeCardViewHolder>() {

    private val inflator: LayoutInflater = LayoutInflater.from(context)
    private var pokeCards= emptyList<PokemonCard>()

    inner class PokeCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cardImageView: ImageView = itemView.findViewById(R.id.cardImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeCardViewHolder {
        val itemView = inflator.inflate(R.layout.layout_poke_card_list, parent, false)
        return PokeCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokeCardViewHolder, position: Int) {
        val card = pokeCards[position]

        val cardImageView = holder.itemView.cardImage
        Picasso.get().load(card.getImageUrlHiRes()).into(cardImageView)
    }

    override fun getItemCount(): Int {
        return pokeCards.size
    }

    internal fun setCardList(cardsList: List<PokemonCard>){
        pokeCards = cardsList
        notifyDataSetChanged()
    }
}