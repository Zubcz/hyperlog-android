package com.hypertrack.hyperlog

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner

// Expiry time for logs in seconds
private const val EXPIRY_TIME : Int = 2
private const val URL = "url"

@RunWith(MockitoJUnitRunner::class)
class UnitTests {

    @Mock
    private lateinit var mockContext: Context
    @Mock
    private lateinit var mockSharedPreferences: SharedPreferences
    @Mock
    private lateinit var mockSharedPreferencesEditor: SharedPreferences.Editor


    private fun defineContext() {
        `when`(mockContext.applicationContext)
                .thenReturn(mockContext)
        `when`(mockSharedPreferences.edit())
                .thenReturn(mockSharedPreferencesEditor)
        `when`(mockSharedPreferencesEditor.putString("com.hyperlog:LogFormat", GsonBuilder().create().toJson(LogFormat(mockContext))))
                .thenReturn(mockSharedPreferencesEditor)
        `when`(mockContext.getSharedPreferences("com.hyperlog:SharedPreference", Context.MODE_PRIVATE))
                .thenReturn(mockSharedPreferences)
    }

    //EXPIRY TIME

    @Test
    fun initializeExpiryTime() {
        defineContext()
        HyperLog.initialize(mockContext, EXPIRY_TIME)
        assertEquals(EXPIRY_TIME.toLong(), HyperLog.getExpiryTime())
    }

    // URL

    @Test
    fun setCorrectURL() {
        defineContext()
        HyperLog.initialize(mockContext)
        HyperLog.initialize(mockContext)
        HyperLog.setURL(URL)
        assertEquals(URL, HyperLog.getURL())
    }

    @Test
    fun setIncorrectURL() {
        defineContext()
        HyperLog.initialize(mockContext)
        HyperLog.initialize(mockContext)
        HyperLog.setURL("")
        assertEquals("", HyperLog.getURL())
    }

}