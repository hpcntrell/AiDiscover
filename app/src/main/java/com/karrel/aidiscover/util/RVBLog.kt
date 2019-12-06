package com.karrel.aidiscover.util

import timber.log.Timber

/**
 * Rell, Victhor, BK
 * WE ARE ONE TEAM!!!
 */
object RVBLog {

    internal val TAG = "RVBLog"

    private var ENABLE_LOG = true

    /**
     * 로그를 출력할지를 boolean 값의 파라미터로 전달.
     */
    fun setEnableLog(enableLog: Boolean) {
        ENABLE_LOG = enableLog
    }

    /**
     * Log Level Error
     */
    fun e(message: String) {
        if (!ENABLE_LOG) return

        val elements = Thread.currentThread().stackTrace

        val element = elements[3]
        val path = element.toString()

        Timber.e(String.format("%s -> %s", path, message))
    }

    fun e() {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        val element = elements[3]
        val path = element.toString()

        Timber.e(String.format("%s -> %s", path, getMethodName(path)))
    }

    fun w() {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        val element = elements[3]
        val path = element.toString()

        Timber.w(String.format("%s -> %s", path, getMethodName(path)))
    }

    /**
     * Log Level Warning
     */
    fun w(message: String) {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        val element = elements[3]
        val path = element.toString()

        Timber.w(String.format("%s -> %s", path, message))
    }

    fun i() {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        val element = elements[3]
        val path = element.toString()

        Timber.i(String.format("%s -> %s", path, getMethodName(path)))
    }

    /**
     * Log Level Information
     */
    fun i(message: String) {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        val element = elements[3]
        val path = element.toString()

        Timber.i(String.format("%s -> %s", path, message))
    }

    fun d() {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        val element = elements[3]
        val path = element.toString()


        Timber.d(String.format("%s -> %s", path, getMethodName(path)))
    }

    private fun getMethodName(path: String): String {
        var message = ""
        try {
            val arrStr = path.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (arrStr.size > 2) {
                val str2 = arrStr[arrStr.size - 2]
                message = str2.substring(0, str2.indexOf("("))
            }
        } catch (e: Exception) {

        } finally {
            return "$message()"
        }
    }

    /**
     * Log Level Debug
     */
    fun d(message: String) {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        val element = elements[3]
        val path = element.toString()

        Timber.d(String.format("%s -> %s", path, message))
    }

    fun stack() {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        var element = ""

        elements.forEach {
            element += it.toString() + "\n"
        }

        val path = element

        Timber.d(String.format("%s -> %s", path, getMethodName(elements[3].toString())))
    }

    fun stack(message: String) {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        var element = ""

        elements.forEach {
            element += it.toString() + "\n"
        }

        val path = element

        Timber.d(String.format("%s -> %s", path, message))
    }

    fun v() {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        val element = elements[3]
        val path = element.toString()

        Timber.v(String.format("%s -> %s", path, getMethodName(path)))
    }

    /**
     * Log Level Verbose
     */
    fun v(message: String) {
        if (!ENABLE_LOG) return
        val elements = Thread.currentThread().stackTrace

        val element = elements[3]
        val path = element.toString()

        Timber.v(String.format("%s -> %s", path, message))
    }
}
