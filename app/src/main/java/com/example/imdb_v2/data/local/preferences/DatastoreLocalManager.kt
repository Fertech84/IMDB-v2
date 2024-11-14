package com.example.imdb_v2.data.local.preferences

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class DatastoreLocalManager(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "settings")
        private val USER_ID = intPreferencesKey("user_id")
    }

    val getUserID: Flow<Int> = context.dataStore.data.map { preferences->
        preferences[ USER_ID]?: -1
    }

    suspend fun saveUserId(id: Int){
        context.dataStore.edit { preferences->
            preferences[ USER_ID] = id
        }
    }
}