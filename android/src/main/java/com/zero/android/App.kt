package com.zero.android

import android.app.Application
import com.arkivanov.mvikotlin.timetravel.server.TimeTravelServer
import com.zero.common.db.appContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = this

        TimeTravelServer().start()
    }
}
