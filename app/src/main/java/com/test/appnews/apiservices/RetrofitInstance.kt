package com.test.appnews.apiservices


import com.test.appnews.Constant.Companion.apikey
import com.test.appnews.Constant.Companion.country
import com.test.appnews.Constant.Companion.domain
import com.test.appnews.models.newsmodels.NewsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitInstance {




    interface ApiService {


        @GET("v2/top-headlines")
        suspend fun getTopHeadlinesListArticle(@Query("country") countryS: String = country,
                                               @Query("category") category: String,
                                               @Query("apiKey") apiKey: String = apikey): NewsResponse
    }

    object RetrofitInstance {




        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
    }


}