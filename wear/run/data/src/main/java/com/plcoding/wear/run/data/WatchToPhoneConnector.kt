package com.plcoding.wear.run.data

import com.plcoding.core.connectivity.domain.DeviceNode
import com.plcoding.core.connectivity.domain.DeviceType
import com.plcoding.core.connectivity.domain.NodeDiscovery
import com.plcoding.wear.run.domain.PhoneConnector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WatchToPhoneConnector(
    nodeDiscovery: NodeDiscovery,
    applicationScope: CoroutineScope
) : PhoneConnector {
    private val _connectedNote = MutableStateFlow<DeviceNode?>(null)
    override val connectedNode = _connectedNote.asStateFlow()

    val messagingActions = nodeDiscovery
        .observeConnectedDevices(DeviceType.WATCH)
        .onEach { connectedNotes ->
            val node = connectedNotes.firstOrNull()
            if (node != null && node.isNearby) {
                _connectedNote.value = node
            }
        }
        .launchIn(applicationScope)
}