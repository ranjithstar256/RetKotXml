package com.example.retkotxml
import retrofit2.Call
import retrofit2.http.GET

// Data class to model the response

interface ApiService {
    @GET("posts/1")
    fun getPost(): Call<Post>
}
