package com.test.appnews.respository

import com.test.appnews.apiservices.RetrofitInstance
import com.test.appnews.models.newsmodels.NewsResponse

class NewsRepository {
    private val api = RetrofitInstance.RetrofitInstance.api

    suspend fun getData(country :String,category :String,apiKey :String): NewsResponse {
        return api.getTopHeadlinesListArticle(country,category,apiKey)
    }
}