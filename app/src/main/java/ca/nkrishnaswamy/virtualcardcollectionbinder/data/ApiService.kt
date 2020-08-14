package ca.nkrishnaswamy.virtualcardcollectionbinder.data

import ca.nkrishnaswamy.virtualcardcollectionbinder.data.response.PokemonCardPageResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val baseUrl = "https://api.pokemontcg.io/v1/"

interface ApiService {
    @GET("cards")
    fun getCardPage(
        @Query("name") cardName: String,
        @Query("hp") hp: String,
        @Query("set") setName: String,
        @Query("number") setNum: String,
        @Query("supertype") superType: String,
        @Query("subtype") subType: String
    ): Deferred<PokemonCardPageResponse>

    companion object {
        operator fun invoke(): ApiService {
            val reqInterceptor = Interceptor {chain ->

                val url = chain.request().url.newBuilder().addQueryParameter("cards","?").build()
                val request = chain.request().newBuilder().url(url).build()
                return@Interceptor chain.proceed(request)
            }
            lateinit var cardsPageResponse: PokemonCardPageResponse
            val okHttpClient = OkHttpClient.Builder().addInterceptor(reqInterceptor).build()
            val pokeCardPageDeserializer = GsonBuilder().registerTypeAdapter(PokemonCardPageResponse::class.java, GetPokemonCardPageDeserializer()).create()

            val apiService =  Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl).addCallAdapterFactory(CoroutineCallAdapterFactory()).addConverterFactory(GsonConverterFactory.create(pokeCardPageDeserializer)).build().create(ApiService::class.java)

            return apiService
        }
    }
}