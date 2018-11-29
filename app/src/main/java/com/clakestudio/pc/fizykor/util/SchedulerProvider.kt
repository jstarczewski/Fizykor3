package com.clakestudio.pc.fizykor.util

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun uiScheduler(): Scheduler
    fun ioScheduler(): Scheduler

}