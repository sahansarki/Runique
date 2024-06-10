package com.plcoding.run.presentation

sealed interface RunOverViewAction {
    data object OnStartClick: RunOverViewAction
    data object OnLogoutClick: RunOverViewAction
    data object OnAnalyticsClick: RunOverViewAction
}