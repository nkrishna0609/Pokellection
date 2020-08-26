package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard

@Dao
interface UserCardsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: PokemonCard)

    @Query("SELECT * FROM pokemonCardsTable WHERE card_name LIKE :search " + "OR card_evolvesFrom LIKE :search " + "OR card_seriesName LIKE :search " + "OR card_setName LIKE :search")
    fun searchCards(search: String) : LiveData<List<PokemonCard>>

    @Query("SELECT * FROM pokemonCardsTable")
    fun getAllCards() : LiveData<List<PokemonCard>>

    @Delete
    suspend fun deleteCard(card: PokemonCard)

    @Query("DELETE FROM pokemonCardsTable")
    suspend fun deleteAll()
}