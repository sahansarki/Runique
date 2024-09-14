package com.plcoding.core.domain.run

import com.plcoding.core.domain.util.DataError
import kotlinx.coroutines.flow.Flow

typealias RunId = String

interface LocalRunDataSource {
    fun getRuns(): Flow<List<Run>>
    suspend fun upsertRun(run: Run): com.plcoding.core.domain.util.Result<RunId, DataError.Local>
    suspend fun upsertRuns(runs: List<Run>): com.plcoding.core.domain.util.Result<List<RunId>, DataError.Local>
    suspend fun deleteRun(id: String)
    suspend fun deleteAllRuns()
}