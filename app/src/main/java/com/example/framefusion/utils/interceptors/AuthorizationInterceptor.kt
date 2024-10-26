package com.example.framefusion.utils.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    private val X_API_KEY = "EB5RC3V-SQR4HTS-Q1DPVG0-86M9TK1"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("X-API-KEY", X_API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}