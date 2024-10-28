package com.plcoding.wear.run.domain

import com.plcoding.core.connectivity.domain.DeviceNode
import com.plcoding.core.connectivity.domain.messages.MessagingAction
import com.plcoding.core.connectivity.domain.messages.MessagingError
import com.plcoding.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PhoneConnector {
    val connectedNode: StateFlow<DeviceNode?>
    val messagingActions: Flow<MessagingAction>

    suspend fun sendActionToPhone(action: MessagingAction): EmptyResult<MessagingError>
}