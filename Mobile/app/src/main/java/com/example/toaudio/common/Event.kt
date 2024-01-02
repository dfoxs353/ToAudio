package com.example.toaudio.common

interface EventHandler<E> {
    fun obtainEvent(event: E)

}