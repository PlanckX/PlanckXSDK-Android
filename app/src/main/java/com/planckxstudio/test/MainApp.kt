package com.planckxstudio.test

import android.app.Application
import com.planckxstudio.sdk.PlanckxStudio

/**
 *
 * Description:
 * Author: ricky
 * CreateDate: 2021/11/11 9:38 上午
 */

class MainApp : Application() {
    init {
        PlanckxStudio.init("JISQ97ih", "3e04178595c34ee6131f10a81bbdb99fbb421e8b")
    }
}