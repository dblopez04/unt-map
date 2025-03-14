package com.example.dpmap.network

import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType

/*
private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
//"https://ci.unt.edu/sites/default/files/"

 */

/*
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

 */

interface DPApiService {
    @GET("photos")
    //@GET("discovery_park_map.pdf")
    suspend fun getMap(): List<DPMap>
}

/*
object DPApi {
    /*
    val retrofitService : DPApiService by lazy {
        retrofit.create(DPApiService::class.java)
    }

     */
}

 */