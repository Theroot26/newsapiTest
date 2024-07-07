package com.test.appnews

class Constant {


    companion object{
        var domain = "https://newsapi.org/"
        var apikey = "5bca30152455448085765946a72e86a1"

        var country = "us"
            get() {
                println("Getting the name")
                return field
            }
            set(value) {
                println("Setting the name")
                field = value
            }

    }

}