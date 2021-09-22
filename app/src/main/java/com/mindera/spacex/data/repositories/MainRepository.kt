package com.mindera.spacex.data.repositories

import com.softweb.kotlinmvvm.data.api.RetrofitInstance

class MainRepository() {

    suspend fun getCompanyInfo() =
        RetrofitInstance.api.getCompanyInfo()

    suspend fun getLaunches() =
        RetrofitInstance.api.getLaunches()
}