package ca.nkrishnaswamy.virtualcardcollectionbinder

import androidx.lifecycle.LiveData
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.UserPokeCardRoomDb
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard
import ca.nkrishnaswamy.virtualcardcollectionbinder.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class cardsRepository(private val db: UserPokeCardRoomDb) {

    val userCards: LiveData<List<PokemonCard>> = db.localPokeCardDao().getAllCards()

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
                db.localPokeCardDao().insertCards(pokeCardPage.getPokemonCards())
            }
    }
}