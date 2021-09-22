package com.mindera.spacex.ui.viewmodels


import com.mindera.spacex.utils.Resource
import retrofit2.Response

class ViewModelHelper<T> {

    fun handleCompanyInfoResponse(response: Response<T>) : Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}