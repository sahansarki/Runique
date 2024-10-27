package com.plcoding.core.connectivity.data

import com.google.android.gms.wearable.Node
import com.plcoding.core.connectivity.domain.DeviceNode

fun Node.toDevice(): DeviceNode {
    return DeviceNode(
        id,
        displayName,
        isNearby
    )
}