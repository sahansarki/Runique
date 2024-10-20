@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.plcoding.run.presentation.run_overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.core.presentation.designsystem.AnalyticsIcon
import com.plcoding.core.presentation.designsystem.LogoIcon
import com.plcoding.core.presentation.designsystem.LogoutIcon
import com.plcoding.core.presentation.designsystem.RunIcon
import com.plcoding.core.presentation.designsystem.RuniqueTheme
import com.plcoding.core.presentation.designsystem.components.RuniqueFloatingActionButton
import com.plcoding.core.presentation.designsystem.components.RuniqueScaffold
import com.plcoding.core.presentation.designsystem.components.RuniqueToolbar
import com.plcoding.core.presentation.designsystem.components.util.DropDownItem
import com.plcoding.run.presentation.R
import com.plcoding.run.presentation.run_overview.components.RunListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunOverviewScreenRoot(
    onStartRunClick: () -> Unit,
    onLogOutClick: () -> Unit,
    viewModel: RunOverviewModel = koinViewModel()
) {
    RunOverviewScreen(state = viewModel.state, onAction = { action ->
        when (action) {
            RunOverViewAction.OnStartClick -> onStartRunClick.invoke()
            RunOverViewAction.OnLogoutClick -> onLogOutClick.invoke()
            else -> Unit
        }

        viewModel.onAction(action)
    })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RunOverviewScreen(
    onAction: (RunOverViewAction) -> Unit, state: RunOverviewState
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    RuniqueScaffold(topAppBar = {
        RuniqueToolbar(
            showBackButton = false,
            title = stringResource(id = R.string.runique),
            scrollBehavior = scrollBehavior,
            menuItems = listOf(
                DropDownItem(
                    icon = AnalyticsIcon, title = stringResource(id = R.string.analytics)
                ),

                DropDownItem(
                    icon = LogoutIcon, title = stringResource(id = R.string.log_out)
                ),
            ),
            onMenuItemClick = { index ->
                when (index) {
                    0 -> onAction(RunOverViewAction.OnAnalyticsClick)
                    1 -> onAction(RunOverViewAction.OnLogoutClick)
                }
            },
            startContent = {
                Icon(
                    imageVector = LogoIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(30.dp)
                )
            },
        )
    }, floatingActionButton = {
        RuniqueFloatingActionButton(icon = RunIcon, onClick = {
            onAction(RunOverViewAction.OnStartClick)
        })
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(horizontal = 16.dp),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = state.runs, key = { it.id }) {
                RunListItem(
                    runUi = it,
                    onDeleteClick = {
                        onAction(RunOverViewAction.DeleteRun(it))
                    },
                    modifier = Modifier
                        .animateItemPlacement()
                )
            }
        }
    }
}

@Preview
@Composable
private fun RunOverViewActionScreenPreview() {
    RuniqueTheme {
        RunOverviewScreen(
            onAction = {}, state = RunOverviewState()
        )
    }
}