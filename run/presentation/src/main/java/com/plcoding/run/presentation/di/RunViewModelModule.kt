package com.plcoding.run.presentation.di

import com.plcoding.run.presentation.active_run.ActiveRunViewModel
import com.plcoding.run.presentation.run_overview.RunOverviewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val runViewModelModule = module {
    viewModelOf(::RunOverviewModel)
    viewModelOf(::ActiveRunViewModel)

}