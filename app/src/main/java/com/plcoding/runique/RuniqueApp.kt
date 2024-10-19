package com.plcoding.runique

import android.app.Application
import com.plcoding.auth.data.di.authDataModule
import com.plcoding.auth.presentation.di.authPresentationModule
import com.plcoding.core.data.di.coreDataModule
import com.plcoding.core.database.di.databaseModule
import com.plcoding.run.data.di.runDataModule
import com.plcoding.run.location.di.locationModule
import com.plcoding.run.presentation.di.runPresentationModule
import com.plcoding.runique.di.appModule
import com.plconidg.network.di.networkModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@RuniqueApp)
            workManagerFactory()
            modules(
                authDataModule,
                authPresentationModule,
                appModule,
                coreDataModule,
                runPresentationModule,
                locationModule,
                databaseModule,
                networkModule,
                runDataModule
            )
        }
    }
}