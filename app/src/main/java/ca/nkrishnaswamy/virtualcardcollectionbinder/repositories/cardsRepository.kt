package ca.nkrishnaswamy.virtualcardcollectionbinder.repositories

import androidx.lifecycle.LiveData
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.DAOs.UserCardsDAO
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.roomDbs.UserPokeCardRoomDb
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard
import ca.nkrishnaswamy.virtualcardcollectionbinder.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class cardsRepository(private val cardDao: UserCardsDAO) {

    suspend fun getUserCardsFromApi(cardName: String,hp: String,setName: String, pokeCardNumber: String,superType: String, subType: String){
            withContext(Dispatchers.IO) {
                val apiService = ApiService()
                val pokeCardPage = apiService.getCardPage(
                    cardName,
                    hp,
                    setName,
                    pokeCardNumber,
                    superType,
                    subType
                ).await()
                cardDao.insertCards(pokeCardPage.getPokemonCards())
            }
    }

    suspend fun getAllCardsInDb(): LiveData<List<PokemonCard>> {
        return cardDao.getAllCards()
    }

    suspend fun deleteCard(card: PokemonCard){
        cardDao.deleteCard(card)
    }

    suspend fun searchCards(search: String){
        cardDao.searchCards(search)
    }
}