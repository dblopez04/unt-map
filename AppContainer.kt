package com.example.dpmap.data

//import com.example.dpmap.network.BASE_URL
import com.example.dpmap.network.DPApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val dpMapRepository: DPMapRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService : DPApiService by lazy {
        retrofit.create(DPApiService::class.java)
    }

    override val dpMapRepository: DPMapRepository by lazy {
        NetworkDPMapRepository(retrofitService)
    }
}