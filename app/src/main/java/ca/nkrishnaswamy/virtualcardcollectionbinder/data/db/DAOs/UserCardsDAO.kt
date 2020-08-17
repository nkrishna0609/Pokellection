package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard

@Dao
interface UserCardsDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertCards(cardList: ArrayList<PokemonCard>)

    @Query("SELECT * FROM pokemonCardsTable WHERE card_name LIKE :search " + "OR card_evolvesFrom LIKE :search " + "OR card_seriesName LIKE :search " + "OR card_setName LIKE :search")
    suspend fun searchCards(search: String) : List<PokemonCard>

    @Query("SELECT * FROM pokemonCardsTable")
    suspend fun getAllCards() : LiveData<List<PokemonCard>>

    @Delete
    suspend fun deleteCard(card: PokemonCard)

}