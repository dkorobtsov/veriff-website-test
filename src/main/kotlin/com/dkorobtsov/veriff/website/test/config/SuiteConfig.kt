package com.dkorobtsov.veriff.website.test.config

import org.aeonbits.owner.Config
import org.aeonbits.owner.Reloadable

interface SuiteConfig : Reloadable {

    @Config.DefaultValue("https://www.veriff.com")
    fun baseUrl(): String

    @Config.DefaultValue("chrome")
    fun targetBrowser(): String

    @Config.DefaultValue("false")
    fun isHeadless(): Boolean

    @Config.DefaultValue("true")
    fun isDebug(): Boolean

    @Config.DefaultValue("http://localhost:4444/wd/hub")
    fun selenoidUrl(): String


}
