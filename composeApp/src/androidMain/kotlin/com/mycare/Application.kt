package com.mycare

import android.app.Application
import com.mycare.core.network.di.networkModule
import com.mycare.core.ui.di.CoreUiModule
import com.mycare.di.mainModule
import com.mycare.feature.appointments.di.AppointmentsModule
import com.mycare.feature.history.di.HistoryModule
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    mainModule,
                    AppointmentsModule().module,
                    CoreUiModule().module,
                    networkModule,
                    HistoryModule().module,
                ),
            )
        }
    }
}
