package com.example.amex.interfaces

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MyMLApi {
    data class MyRequest(
        val features: List<String>
    )
    data class MyResponse(val prediction: Int)
    @POST("prediction")
    fun postData(@Body request: MyRequest): Call<MyResponse>
}