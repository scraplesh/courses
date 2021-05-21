package me.scraplesh.courses.navigation

import kotlinx.coroutines.flow.FlowCollector

interface Coordinator : FlowCollector<NavEvent>
