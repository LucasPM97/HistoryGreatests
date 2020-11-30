package com.lucas.historygreatests.utils.helpers

import android.content.Context
import androidx.core.content.edit
import com.lucas.historygreatests.R
import java.util.*
import java.util.concurrent.TimeUnit

object DatabaseHelper {

    fun checkIfDatabaseIsExpired(context: Context, dbExpiredStringId: Int): Boolean {
        val preferences = context.getSharedPreferences(
            context.getString(R.string.greatest_settings),
            Context.MODE_PRIVATE
        )

        val dbExpiredString = context.getString(dbExpiredStringId)

        if (!preferences.contains(dbExpiredString)) return true

        val expireDate =
            Date(preferences.getLong(dbExpiredString, 0))

        return DatetimeHelper.checkIfExpirationDateHasExpired(expireDate)
    }

    fun addDatabaseIsExpired(context: Context, dbExpiredStringId: Int) {
        val preferences = context.getSharedPreferences(
            context.getString(R.string.greatest_settings),
            Context.MODE_PRIVATE
        )

        preferences.edit(commit = true) {
            putLong(
                context.getString(dbExpiredStringId),
                DatetimeHelper.getCurrentDate().time
            )
        }
    }
}