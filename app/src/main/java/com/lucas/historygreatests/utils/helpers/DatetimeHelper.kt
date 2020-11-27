package com.lucas.historygreatests.utils.helpers

import java.util.*
import java.util.concurrent.TimeUnit

object  DatetimeHelper {

    fun getCurrentDate() = Calendar.getInstance().time

    fun getDaysBetweenDates(initDate: Date, endDate: Date):Long {
        val diff = endDate.time - initDate.time

        val result = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)

        return result
    }

    fun checkIfExpirationDateHasExpired(expirationDate: Date): Boolean {
        return getDaysBetweenDates(expirationDate,getCurrentDate()) > 1
    }
}