package ca.nkrishnaswamy.virtualcardcollectionbinder.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.roomDbs.UserPokeCardRoomDb
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard
import ca.nkrishnaswamy.virtualcardcollectionbinder.repositories.CardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserCardsViewModel(application: Application) : AndroidViewModel(application) {

    val cardsDao = UserPokeCardRoomDb.getInstance(application).localPokeCardDao()
    private val repository = CardsRepository(cardsDao)
    private val allUserCards = repository.getAllCardsInDb()

    fun insertCard(card: PokemonCard)=viewModelScope.launch(Dispatchers.IO) {
        repository.insertCard(card)
    }

    fun getAllCardsInDb(): LiveData<List<PokemonCard>>{
        return allUserCards
    }

    fun deleteCard(card: PokemonCard) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteCard(card)
    }

    fun searchCardsFromDb(search: String): LiveData<List<PokemonCard>>{
        return repository.searchCards(search)
    }

    suspend fun retrieveCardsFromApi(cardName: String, hp: String, setName: String, pokeCardNumber: String, superType: String, subType: String): List<PokemonCard>{
        return repository.getUserCardsFromApi(cardName, hp, setName, pokeCardNumber, superType, subType)
    }
}