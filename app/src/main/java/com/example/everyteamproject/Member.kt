package com.example.everyteamproject

import java.io.Serializable

/*
data class Member(
    var name:String
    )*/

class Member: Serializable{
    var id: Int ?= 0
    var name: String ?= null
    var role: String ?= null
    var day: String ?= null
    var time: String?= null
}
