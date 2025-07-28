package com.lxdesign.freeboxtv

import java.net.URL

fun main() {
    val m3uUrl = "http://mafreebox.freebox.fr/freeboxtv/playlist.m3u"
    val lines = URL(m3uUrl).readText().lines()
    val channels = lines.chunked(2)
        .filter { it.size == 2 && it[0].startsWith("#EXTINF") }
        .map {
            val name = it[0].substringAfter(",").trim()
            val url = it[1].trim()
            Channel(name, url)
        }

    println("Chaînes disponibles :")
    channels.take(10).forEachIndexed { index, channel ->
        println("${index + 1}. ${channel.name} -> ${channel.url}")
    }
}

data class Channel(val name: String, val url: String)