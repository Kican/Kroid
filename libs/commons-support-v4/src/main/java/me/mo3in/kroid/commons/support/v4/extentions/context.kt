package me.mo3in.kroid.commons.support.v4.extentions

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.v4.app.Fragment

inline val Fragment.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(activity)