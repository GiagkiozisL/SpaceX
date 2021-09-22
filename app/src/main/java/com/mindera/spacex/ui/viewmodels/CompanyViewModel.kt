package com.mindera.spacex.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindera.spacex.data.models.Company
import com.mindera.spacex.data.models.Launch
import com.mindera.spacex.data.repositories.MainRepository
import com.mindera.spacex.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CompanyViewModel(private val mainRepository: MainRepository): ViewModel() {

    val company: MutableLiveData<Resource<Company>> = MutableLiveData()
    val launches: MutableLiveData<Resource<List<Launch>>> = MutableLiveData()

    fun getCompanyInfo() = viewModelScope.launch {
        company.postValue(Resource.Loading())
        val response = mainRepository.getCompanyInfo()
        val responseData = handleCompanyInfoResponse(response)
        company.postValue(responseData)
    }

    fun getLaunches() = viewModelScope.launch {
        launches.postValue(Resource.Loading())
        val response = mainRepository.getLaunches()
        val responseData = handleLaunchesResponse(response)
        launches.postValue(responseData)
    }

    private fun handleCompanyInfoResponse(response: Response<Company>) : Resource<Company> {
        return ViewModelHelper<Company>().handleCompanyInfoResponse(response)
    }

    private fun handleLaunchesResponse(response: Response<List<Launch>>) : Resource<List<Launch>> {
        return ViewModelHelper<List<Launch>>().handleCompanyInfoResponse(response)
    }

}