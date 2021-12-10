package com.planckxstudio.test

import android.app.Application
import com.planckxstudio.sdk.PlanckxStudio

class MainApp : Application() {
    init {
        PlanckxStudio.init("ptDoWYvN", "c07b338cb51a81e14f6db2e1a3a2b9a1c7570e68")
    }
}