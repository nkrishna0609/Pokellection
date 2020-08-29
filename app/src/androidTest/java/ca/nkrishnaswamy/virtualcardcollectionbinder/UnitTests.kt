package ca.nkrishnaswamy.virtualcardcollectionbinder

import android.content.Context
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.db.roomDbs.UserPokeCardRoomDb
import ca.nkrishnaswamy.virtualcardcollectionbinder.data.models.PokemonCard
import ca.nkrishnaswamy.virtualcardcollectionbinder.repositories.CardsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.io.IOException
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UnitTests {

    private lateinit var context: Context
    private lateinit var db: UserPokeCardRoomDb

    @Mock
    lateinit var observer: Observer<List<PokemonCard>>

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, UserPokeCardRoomDb::class.java).build()
        MockitoAnnotations.initMocks(this)
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun searchApiForCardTest() {
        val cardsDao = db.localPokeCardDao()
        val repository = CardsRepository(cardsDao)
        CoroutineScope(Dispatchers.IO).launch {
            val cardsList = repository.getUserCardsFromApi(
                context,
                "Charizard",
                "",
                "Legendary Treasures",
                "19"
            )
            assertEquals("Charizard", cardsList[0].getName())
        }
    }

    @Test
    @Throws(Exception::class)
    fun enterCardIntoDbTest() {
        val cardsDao = db.localPokeCardDao()
        val repository = CardsRepository(cardsDao)
        CoroutineScope(Dispatchers.IO).launch {
            val cardsList = repository.getUserCardsFromApi(
                context,
                "Charizard",
                "",
                "Legendary Treasures",
                "19"
            )
            cardsDao.insertCard(cardsList[0])
            val insertedCardName = cardsDao.searchCardBySetNameSetNum("Legendary Treasures", "19").getName()
            assertNotNull(cardsDao.searchCardBySetNameSetNum("Legendary Treasures","19"))
            withContext(Dispatchers.Main) {
                assertEquals(cardsList[0].getName(),insertedCardName)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun enterDuplicateCardIntoDbTest() {
        val cardsDao = db.localPokeCardDao()
        val repository = CardsRepository(cardsDao)
        CoroutineScope(Dispatchers.IO).launch {
            val cardsList = repository.getUserCardsFromApi(
                context,
                "Charizard",
                "",
                "Legendary Treasures",
                "19"
            )
            cardsDao.insertCard(cardsList[0])
            cardsDao.insertCard(cardsList[0])
            assertEquals(1, cardsDao.getRowCount())
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteCardFromDbTest() {
        val cardsDao = db.localPokeCardDao()
        val repository = CardsRepository(cardsDao)
        CoroutineScope(Dispatchers.IO).launch {
            val cardsList = repository.getUserCardsFromApi(
                context,
                "Charizard",
                "",
                "Legendary Treasures",
                "19"
            )
            val cardToInsert: PokemonCard = cardsList[0]
            val insertedId = cardsDao.insertCard(cardToInsert)
            cardToInsert.setId(insertedId)
            cardsDao.deleteCard(cardToInsert)
            assertNull(cardsDao.searchCardBySetNameSetNum("Legendary Treasures","19"))
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllCardsFromDbTest() {
        val cardsDao = db.localPokeCardDao()
        val repository = CardsRepository(cardsDao)
        CoroutineScope(Dispatchers.IO).launch {
            val cardsList = repository.getUserCardsFromApi(
                context,
                "Charizard",
                "",
                "",
                "19"
            )
            for (card in cardsList){
                cardsDao.insertCard(card)
            }
            cardsDao.deleteAll()
            assertNull(cardsDao.searchCardBySetNameSetNum("Legendary Treasures","19"))
        }
    }

    @Test
    @Throws(Exception::class)
    fun getAllCardsTest(){
        val cardsDao = db.localPokeCardDao()
        val repository = CardsRepository(cardsDao)
        CoroutineScope(Dispatchers.IO).launch {
            val cardsList = repository.getUserCardsFromApi(
                context,
                "Charizard",
                "",
                "",
                ""
            )
            withContext(Dispatchers.Main) {
                cardsDao.getAllCards().observeForever(observer)
                withContext(Dispatchers.IO) {
                    for (card in cardsList) {
                        cardsDao.insertCard(card)
                    }
                }
                for (card in cardsList){
                    verify(observer).onChanged(Collections.singletonList(card))
                }
            }
        }
    }
}