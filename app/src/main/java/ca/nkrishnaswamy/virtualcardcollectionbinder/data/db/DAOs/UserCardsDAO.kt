package ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.DAOs

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard

@Dao
interface UserCardsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: PokemonCard): Long

    @Query("SELECT * FROM pokemonCardsTable")
    fun getAllCards() : LiveData<List<PokemonCard>>

    @Delete
    suspend fun deleteCard(card: PokemonCard)

    @Query("DELETE FROM pokemonCardsTable")
    suspend fun deleteAll()

    @Query("SELECT * FROM pokemonCardsTable WHERE card_setName LIKE :setNameSearch " + "AND card_num LIKE :setNumSearch")
    fun searchCardBySetNameSetNum(setNameSearch: String, setNumSearch: String) : PokemonCard

    @Query("SELECT COUNT(card_name) FROM pokemonCardsTable")
    fun getRowCount(): Int
}