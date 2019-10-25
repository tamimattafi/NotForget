package ru.tsu.ibrahimfall.notforget.utils

import java.io.PrintWriter
import java.io.StringWriter

object Utilities {

    fun bytesToHex(data: ByteArray): String {
        val response = StringBuilder()
        for (b in data) {
            response.append(String.format("%02X", b)).append(" ")
        }
        return response.toString().trim { it <= ' ' }
    }

    fun bytesToHex(data: Byte): String {
        val response = StringBuilder()
        response.append(String.format("%02X", data)).append(" ")
        return response.toString().trim { it <= ' ' }
    }

    fun bytesToString(data: ByteArray): String {
        val response = StringBuilder()
        for (b in data) {
            response.append(String.format("%c", b)).append("")
        }
        return response.toString().trim { it <= ' ' }
    }

    fun getStackTrace(e: Exception): String {
        val errors = StringWriter()
        val writer = PrintWriter(errors)
        e.printStackTrace(writer)
        return errors.toString().replace("\t".toRegex(), "").replace("\n".toRegex(), " | ")
            .replace("\r".toRegex(), "").trim { it <= ' ' }
    }
}
