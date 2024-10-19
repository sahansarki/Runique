package com.plcoding.run.presentation.run_overview

import com.plcoding.run.presentation.run_overview.model.RunUi

sealed interface RunOverViewAction {
    data object OnStartClick: RunOverViewAction
    data object OnLogoutClick: RunOverViewAction
    data object OnAnalyticsClick: RunOverViewAction
    data class DeleteRun(val runUi: RunUi): RunOverViewAction
}