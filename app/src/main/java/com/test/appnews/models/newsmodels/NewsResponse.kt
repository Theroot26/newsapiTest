package com.test.appnews.models.newsmodels

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)