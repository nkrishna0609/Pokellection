package ca.nkrishnaswamy.virtualcardcollectionbinder.repositories

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.DAOs.UserCardsDAO
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard
import ca.nkrishnaswamy.virtualcardcollectionbinder.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardsRepository(private val cardDao: UserCardsDAO) {

    suspend fun getUserCardsFromApi(context: Context, cardName: String,hp:String,setName: String, pokeCardNumber: String) = withContext(Dispatchers.IO){
        val apiService = ApiService(context = context)
        apiService.getCardPage(cardName, hp, setName, pokeCardNumber).await().getPokemonCards()
    }

    fun getAllCardsInDb(): LiveData<List<PokemonCard>> {
        return cardDao.getAllCards()
    }

    suspend fun deleteCard(card: PokemonCard){
        cardDao.deleteCard(card)
    }

    fun searchCards(search: String): LiveData<List<PokemonCard>>{
        return cardDao.searchCards(search)
    }
    suspend fun insertCard(card: PokemonCard){
        cardDao.insertCard(card)
    }

    suspend fun deleteAllCards(){
        cardDao.deleteAll()
    }

}