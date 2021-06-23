package ru.emba.cbs.uikit.toolbar

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.isActive

fun CbsToolbar.leftButtonClicks(): Flow<Unit> = channelFlow {
    setOnClickListener { if (isActive) trySend(Unit) }
    awaitClose { setOnClickListener(null) }
}