package com.plcoding.core.data.networking.di

import com.plcoding.core.data.networking.HttpClientFactory
import com.plcoding.core.data.networking.auth.EncryptedSessionStorage
import com.plcoding.core.domain.util.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }

    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()


}