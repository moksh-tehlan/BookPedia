package com.moksh.bookpedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform