package com.example.amex.models

data class MessageModel(
    var message:String? = "",
    var senderId:String? = "",
    var timeStamp:Long? = 0
){
    //time stamp is not necessary
}