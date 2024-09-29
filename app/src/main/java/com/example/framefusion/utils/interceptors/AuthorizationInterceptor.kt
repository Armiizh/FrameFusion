package com.example.framefusion.utils.interceptors

import com.example.framefusion.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("X-API-KEY", Constants.X_API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}