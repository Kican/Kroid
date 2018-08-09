package me.mo3in.kroid.commons.services

import java.util.HashMap

/**
 * for holding configuration data
 */
object KServiceConfig {
    var config = HashMap<String, Any>();

    private val DEFAULT_LANG = "default_lang"


    fun setDefaultLang(lang: String): KServiceConfig {
        config[DEFAULT_LANG] = lang;
        return this
    }

    fun getDefaultLang(): String {
        if (config.containsKey(DEFAULT_LANG))
            return config[DEFAULT_LANG].toString()
        return "en"
    }
}