package com.plcoding.auth.data

import com.plcoding.auth.domain.AuthRepository
import com.plcoding.core.data.networking.post
import com.plcoding.core.domain.util.AuthInfo
import com.plcoding.core.domain.util.DataError
import com.plcoding.core.domain.util.EmptyResult
import com.plcoding.core.domain.util.Result
import com.plcoding.core.domain.util.SessionStorage
import com.plcoding.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
): AuthRepository {
    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(
                email, password
            )
        )

        if (result is Result.Success) {
            val data = result.data
            sessionStorage.set(
                AuthInfo(
                    accessToken = data.accessToken,
                    refreshToken = data.refreshToken,
                    userId = data.userId
                )
            )
        }

        return result.asEmptyDataResult()

    }

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email, password
            )
        )
    }
}