package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard

@Dao
interface UserCardsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: PokemonCard)

    @Query("SELECT * FROM pokemonCardsTable")
    fun getAllCards() : LiveData<List<PokemonCard>>

    @Delete
    suspend fun deleteCard(card: PokemonCard)

    @Query("DELETE FROM pokemonCardsTable")
    suspend fun deleteAll()
}