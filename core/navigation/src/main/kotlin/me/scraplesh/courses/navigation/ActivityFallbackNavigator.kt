package me.scraplesh.courses.navigation

//abstract class ActivityFallbackNavigator(
//    activity: FragmentActivity,
//    containerId: Int,
//    fragmentManager: FragmentManager,
//    private val coordinator: Coordinator,
//    private val scope: CoroutineScope
//) : AppNavigator(activity, containerId, fragmentManager) {
//    override fun unexistingActivity(screen: ActivityScreen, activityIntent: Intent) {
//        scope.launch {
//            coordinator.emit(NonExistentActivityNavEvent(screen, activityIntent))
//        }
//    }
//}