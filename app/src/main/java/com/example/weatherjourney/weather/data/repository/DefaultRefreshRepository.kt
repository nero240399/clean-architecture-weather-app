package com.example.weatherjourney.weather.data.repository

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.weatherjourney.weather.data.workers.SuccessWorker
import com.example.weatherjourney.weather.util.SUCCESS_CONNECTIVITY_WORK_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRefreshRepository @Inject constructor(@ApplicationContext context: Context) {

    val refreshFlow = MutableSharedFlow<Boolean>()
    suspend fun emit() = refreshFlow.emit(true)

    private val workManager = WorkManager.getInstance(context)

    val outputWorkInfo: Flow<WorkInfo> =
        workManager.getWorkInfosForUniqueWorkLiveData(SUCCESS_CONNECTIVITY_WORK_NAME).asFlow()
            .map { it.first() }

    fun startListenWhenConnectivitySuccess() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val builder = OneTimeWorkRequestBuilder<SuccessWorker>().setConstraints(constraints)

        workManager.beginUniqueWork(
            SUCCESS_CONNECTIVITY_WORK_NAME,
            ExistingWorkPolicy.KEEP,
            builder.build()
        ).enqueue()
    }
}
