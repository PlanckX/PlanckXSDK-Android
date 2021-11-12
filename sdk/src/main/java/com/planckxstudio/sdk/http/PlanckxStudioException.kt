package com.planckxstudio.sdk.http

/**
 *
 * Description:
 * Author: ricky
 */
internal class PlanckxstudioException : RuntimeException {
    constructor(t: Throwable?) : super(t)
    constructor(msg: String?) : super(msg)
}