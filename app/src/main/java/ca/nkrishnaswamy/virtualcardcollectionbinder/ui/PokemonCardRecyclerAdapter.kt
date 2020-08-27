package ca.nkrishnaswamy.virtualcardcollectionbinder.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ca.nkrishnaswamy.virtualcardcollectionbinder.R
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_poke_card_list.view.*
import java.util.*
import kotlin.collections.ArrayList

class PokemonCardRecyclerAdapter internal constructor(context: Context): RecyclerView.Adapter<PokemonCardRecyclerAdapter.PokeCardViewHolder>(), Filterable {

    private val inflator: LayoutInflater = LayoutInflater.from(context)
    private var pokeCards= mutableListOf<PokemonCard>()
    private var pokeCardsFull =  emptyList<PokemonCard>()

    class PokeCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
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
        pokeCards = cardsList.toMutableList()
        pokeCardsFull = ArrayList(cardsList)
        notifyDataSetChanged()
    }

    fun getPokeCardAt(position: Int): PokemonCard{
        return pokeCards.get(position)
    }

    override fun getFilter(): Filter {
        return pokeCardFilter
    }

    private val pokeCardFilter = object: Filter(){
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filteredList= mutableListOf<PokemonCard>()

            if (p0 == null || p0.isEmpty()){
                filteredList.addAll(pokeCardsFull)
            }
            else{
                val filterString: String = p0.toString().toLowerCase(Locale.ROOT).trim()

                for (item in pokeCardsFull){
                    if ((item.getName().toLowerCase(Locale.ROOT).contains(filterString)) || (item.getEvolvesFrom().toLowerCase(Locale.ROOT).contains(filterString)) || (item.getSubtype().toLowerCase(Locale.ROOT).contains(filterString)) || (item.getSupertype().toLowerCase(Locale.ROOT).contains(filterString)) || (item.getHp().toLowerCase(Locale.ROOT).contains(filterString)) || (item.getRarity().toLowerCase(Locale.ROOT).contains(filterString)) || (item.getSeries().toLowerCase(Locale.ROOT).contains(filterString)) || (item.getSet().toLowerCase(Locale.ROOT).contains(filterString))){
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            pokeCards.clear()
            (p1?.values as? List<PokemonCard>)?.let {pokeCards.addAll(it)}
            notifyDataSetChanged()
        }
    }
}