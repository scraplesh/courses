package ru.emba.cbs.navigation

import kotlinx.coroutines.flow.FlowCollector

interface Coordinator : FlowCollector<NavEvent>
