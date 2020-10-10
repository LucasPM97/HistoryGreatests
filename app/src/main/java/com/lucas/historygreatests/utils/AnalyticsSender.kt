package com.lucas.historygreatests.utils

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.lucas.historygreatests.models.AnalyticsConstans

class AnalyticsSender private constructor() {

    private val firebaseAnalytics = Firebase.analytics

    private object HOLDER {
        val INSTANCE = AnalyticsSender()
    }

    companion object {
        private val instance: AnalyticsSender by lazy { HOLDER.INSTANCE }


        fun trackNavigation(from:String, to:String){
            instance.firebaseAnalytics.logEvent(AnalyticsConstans.Events.Navigation.EVENT){
                param(AnalyticsConstans.Events.Navigation.Params.FROM,from)
                param(AnalyticsConstans.Events.Navigation.Params.TO,to)
            }
        }
    }


}