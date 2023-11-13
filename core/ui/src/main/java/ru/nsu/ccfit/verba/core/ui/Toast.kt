package ru.nsu.ccfit.verba.core.ui

import android.app.Activity
import android.content.Context
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

fun VerbaErrorToast(mContext: Context, message: String) {
    MotionToast.createColorToast(
        mContext as Activity,
        "Failed ☹️",
        message,
        MotionToastStyle.ERROR,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION, null
    )
}

fun VerbaSuccessToast(mContext: Context, message: String) {
    MotionToast.createColorToast(
        mContext as Activity,
        "Success \uD83E\uDD70",
        message,
        MotionToastStyle.SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION, null
    )
}