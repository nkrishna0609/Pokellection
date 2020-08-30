package ca.nkrishnaswamy.virtualcardcollectionbinder.ui.recyclerviewAdaptors

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

class ConfirmCardRecyclerAdapter internal constructor(context: Context,
                                                      private val itemClickListener: OnItemClickListener
): RecyclerView.Adapter<ConfirmCardRecyclerAdapter.PokeCardViewHolder>() {

    private val inflator: LayoutInflater = LayoutInflater.from(context)
    private var pokeCards= mutableListOf<PokemonCard>()

    class PokeCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cardImageView: ImageView = itemView.findViewById(R.id.cardImage)

        fun bind(card: PokemonCard, clickListener: OnItemClickListener){
            itemView.setOnClickListener {
                clickListener.onItemClick(card)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeCardViewHolder {
        val itemView = inflator.inflate(R.layout.layout_poke_card_list, parent, false)
        return PokeCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokeCardViewHolder, position: Int) {
        val card = pokeCards[position]

        val cardImageView = holder.itemView.cardImage
        Picasso.get().load(card.getImageUrlHiRes()).into(cardImageView)

        holder.bind(card, itemClickListener)
    }

    override fun getItemCount(): Int {
        return pokeCards.size
    }

    internal fun setCardList(cardsList: List<PokemonCard>){
        pokeCards = cardsList.toMutableList()
        notifyDataSetChanged()
    }

    fun getPokeCardAt(position: Int): PokemonCard{
        return pokeCards.get(position)
    }

    interface OnItemClickListener{
        fun onItemClick(card: PokemonCard)
    }

}