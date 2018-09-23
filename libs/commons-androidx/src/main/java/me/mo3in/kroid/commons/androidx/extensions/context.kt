package me.mo3in.kroid.commons.androidx.extensions

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment


inline val Fragment.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(activity)