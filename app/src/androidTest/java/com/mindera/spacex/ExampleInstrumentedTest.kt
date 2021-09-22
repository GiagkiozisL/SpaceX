package com.mindera.spacex

import android.icu.util.Calendar
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mindera.spacex.utils.DateTimeUtils

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.mindera.spacex", appContext.packageName)
    }

    @Test
    fun testFutureDate() {
        val daysAhead: Long = 5
        val nowCalendar = Calendar.getInstance()

        val daysAheadMillis = TimeUnit.DAYS.toMillis(daysAhead) + 3000

        val futureCalendar = Calendar.getInstance()
        futureCalendar.timeInMillis = nowCalendar.timeInMillis + daysAheadMillis

        val result = DateTimeUtils.getDaysDifference(futureCalendar.timeInMillis)
        assertEquals(result, daysAhead * (-1))
 }
}