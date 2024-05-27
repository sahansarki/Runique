package com.plcoding.core.domain.util

interface SessionStorage {
    suspend fun get(): AuthInfo?
    suspend fun set(authInfo: AuthInfo?)
}