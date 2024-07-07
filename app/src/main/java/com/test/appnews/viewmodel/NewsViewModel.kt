package com.test.appnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.appnews.models.newsmodels.NewsResponse
import com.test.appnews.respository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel (private val repository: NewsRepository) : ViewModel() {

    private val _data = MutableLiveData<NewsResponse>()
    val data: LiveData<NewsResponse> get() = _data

    fun fetchData(country :String,category :String,apiKey :String) {
        viewModelScope.launch {
            try {
                val response = repository.getData(country, category,apiKey)
                _data.postValue(response)
                println("headline response:$response")
            } catch (e: Exception) {
                // Handle exceptions
                println(e.message)
            }
        }
    }
}