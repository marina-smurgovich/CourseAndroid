package com.yandex.smur.marina.myfinalproject.timer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.yandex.smur.marina.myfinalproject.timer.util.NotificationUtil
import com.yandex.smur.marina.myfinalproject.timer.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(TimerFragment.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
