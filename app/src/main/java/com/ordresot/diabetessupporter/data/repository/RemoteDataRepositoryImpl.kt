package com.ordresot.diabetessupporter.data.repository

import com.ordresot.diabetessupporter.data.NetworkClient
import com.ordresot.diabetessupporter.domain.api.repository.RemoteDataRepository

class RemoteDataRepositoryImpl(
    private val networkClient: NetworkClient
): RemoteDataRepository {

}