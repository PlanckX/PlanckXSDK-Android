package com.planckxstudio.test

import android.app.Application
import com.planckxstudio.sdk.PlanckxStudio

class MainApp : Application() {
    init {
        PlanckxStudio.init("JISQ97ih", "3e04178595c34ee6131f10a81bbdb99fbb421e8b")
    }
}