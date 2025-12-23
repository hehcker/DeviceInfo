package com.hehcker.deviceinfo.data.system

import android.system.Os
import android.system.StructUtsname
import android.util.Log
import java.util.regex.Matcher
import java.util.regex.Pattern

object Linux {
    fun getFormattedKernelVersion(): String =
        formatKernelVersion(Os.uname())

    private fun formatKernelVersion(uname: StructUtsname): String {
        // Example:
        // 4.9.29-g958411d
        // #1 SMP PREEMPT Wed Jun 7 00:06:03 CST 2017
        val VERSION_REGEX =
            "(#\\d+) " +  /* group 1: "#1" */
            "(?:.*?)?" +  /* ignore: optional SMP, PREEMPT, and any CONFIG_FLAGS */
            "((Sun|Mon|Tue|Wed|Thu|Fri|Sat).+)" /* group 2: "Thu Jun 28 11:02:39 PDT 2012" */
        val m: Matcher = Pattern.compile(VERSION_REGEX).matcher(uname.version)
        if (!m.matches()) {
            Log.e("getKernelVersion", "Regex did not match on uname version " + uname.version)
            return "Unavailable"
        }

        // Example output:
        // 4.9.29-g958411d
        // #1 Wed Jun 7 00:06:03 CST 2017
        return StringBuilder().append(uname.release)
            .append("\n")
            .append(m.group(1))
            .append(" ")
            .append(m.group(2)).toString()
    }
}