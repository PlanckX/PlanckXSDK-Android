package com.planckxstudio.test

import android.app.Application
import com.planckxstudio.sdk.PlanckxStudio

class MainApp : Application() {
    init {
        PlanckxStudio.init("e1tx71U3", "8c4840e3007e0d81808e0ee5248acb4722e240db")
    }
}