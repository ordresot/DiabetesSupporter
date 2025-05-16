package com.ordresot.diabetessupporter.data

import com.ordresot.diabetessupporter.data.dto.Response

interface NetworkClient {
    fun makeRequest(dto: Any): Response
    fun isConnected(): Boolean
}