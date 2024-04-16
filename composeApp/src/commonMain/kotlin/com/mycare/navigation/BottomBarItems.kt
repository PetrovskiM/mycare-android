package com.mycare.navigation

import mycare.composeapp.generated.resources.Res
import mycare.composeapp.generated.resources.common_appointments
import mycare.composeapp.generated.resources.common_more
import mycare.composeapp.generated.resources.common_tracking
import mycare.composeapp.generated.resources.common_trials
import mycare.composeapp.generated.resources.ic_appointments_selected
import mycare.composeapp.generated.resources.ic_appointments_unselected
import mycare.composeapp.generated.resources.ic_more
import mycare.composeapp.generated.resources.ic_tracking_selected
import mycare.composeapp.generated.resources.ic_tracking_unselected
import mycare.composeapp.generated.resources.ic_trials
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
internal enum class BottomBarItems(
    val target: LoggedInNode.NavTarget,
    val selectedRes: DrawableResource,
    val unselectedRes: DrawableResource,
    val labelRes: StringResource,
) {
    APPOINTMENTS(
        target = LoggedInNode.NavTarget.Appointments,
        selectedRes = Res.drawable.ic_appointments_selected,
        unselectedRes = Res.drawable.ic_appointments_unselected,
        labelRes = Res.string.common_appointments,
    ),
    TRACKING(
        target = LoggedInNode.NavTarget.Tracking,
        selectedRes = Res.drawable.ic_tracking_selected,
        unselectedRes = Res.drawable.ic_tracking_unselected,
        labelRes = Res.string.common_tracking,
    ),
    TRIALS(
        target = LoggedInNode.NavTarget.Trials,
        selectedRes = Res.drawable.ic_trials,
        unselectedRes = Res.drawable.ic_trials,
        labelRes = Res.string.common_trials,
    ),
    MORE(
        target = LoggedInNode.NavTarget.More,
        selectedRes = Res.drawable.ic_more,
        unselectedRes = Res.drawable.ic_more,
        labelRes = Res.string.common_more,
    );
}