package com.dkorobtsov.veriff.website.test.config

import org.aeonbits.owner.Config
import org.aeonbits.owner.Reloadable

@Config.Sources("classpath:suite.properties")
interface SuiteConfig : Reloadable {

    /**
     * if debug mode is enabled, page screenshots and diff from baseline (if any)
     * will be saved to /screenshots folder
     */
    @Config.DefaultValue("true")
    fun isDebug(): Boolean

    /**
     * NB. Current configuration supports chrome only.
     *
     * To add another browser need to perform following actions:
     * <pre>
     * 1. adjust config/browsers.json
     * 2. pull browser container
     * </pre>
     */
    @Config.DefaultValue("chrome")
    fun targetBrowser(): String

    @Config.DefaultValue("https://www.veriff.com")
    fun baseUrl(): String

    @Config.DefaultValue("false")
    fun isHeadless(): Boolean

    @Config.DefaultValue("http://localhost:4444/wd/hub")
    fun selenoidUrl(): String

}
