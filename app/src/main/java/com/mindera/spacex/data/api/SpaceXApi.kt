package com.softweb.kotlinmvvm.data.api


import com.mindera.spacex.data.models.Company
import com.mindera.spacex.data.models.Launch
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXApi {

    @GET("info")
    suspend fun getCompanyInfo(): Response<Company>

    @GET("launches")
    suspend fun getLaunches(): Response<List<Launch>>//LaunchResponse>
}