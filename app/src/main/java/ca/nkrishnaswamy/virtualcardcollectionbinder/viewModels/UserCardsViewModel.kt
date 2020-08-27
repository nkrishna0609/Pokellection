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

    private val cardsDao = UserPokeCardRoomDb.getInstance(application).localPokeCardDao()
    private val repository = CardsRepository(cardsDao)
    private val allUserCards = repository.getAllCardsInDb()

    private val context = getApplication<Application>().applicationContext

    fun insertCard(card: PokemonCard)=viewModelScope.launch(Dispatchers.IO) {
        repository.insertCard(card)
    }

    fun getAllCardsInDb(): LiveData<List<PokemonCard>>{
        return allUserCards
    }

    fun deleteCard(card: PokemonCard) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteCard(card)
    }

    suspend fun retrieveCardsFromApi(cardName: String, hp: String, setName: String, pokeCardNumber: String): List<PokemonCard>{
        return repository.getUserCardsFromApi(context, cardName, hp, setName, pokeCardNumber)
    }

    suspend fun deleteAllCards(){
        repository.deleteAllCards()
    }

}