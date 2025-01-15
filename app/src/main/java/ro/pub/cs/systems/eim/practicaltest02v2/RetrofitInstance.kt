package ro.pub.cs.systems.eim.practicaltest02v2

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ro.pub.cs.systems.eim.practicaltest02v2.Constants.BASE_URL

object RetrofitInstance {
    private const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"

    val api: DictionaryApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApiService::class.java)
    }
}
