package com.softweb.kotlinmvvm.data.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.mindera.spacex.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val mapper = ObjectMapper().registerModule(KotlinModule())

            Retrofit.Builder()
                .baseUrl(Constants.base_url)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(SpaceXApi::class.java)
        }
    }
}