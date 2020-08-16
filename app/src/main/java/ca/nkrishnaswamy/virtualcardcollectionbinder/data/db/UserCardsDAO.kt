package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard

@Dao
interface UserCardsDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCards(cardList: ArrayList<PokemonCard>)

    @Query("SELECT * FROM pokemonCardsTable WHERE card_name LIKE :search " + "OR card_evolvesFrom LIKE :search " + "OR card_seriesName LIKE :search " + "OR card_setName LIKE :search")
    fun searchCards(search: String) : LiveData<List<PokemonCard>>

    @Query("SELECT * FROM pokemonCardsTable")
    fun getAllCards() : LiveData<List<PokemonCard>>

    @Delete
    fun deleteCard(card: PokemonCard)

}