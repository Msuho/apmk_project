package com.example.myapplication

enum class UserRegex(val regex: Regex) {

    PASSWORD_REGEX(Regex("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{6,15}\$")),
    NAME_REGEX(Regex("^[a-zA-Z0-9]*\$"))

}