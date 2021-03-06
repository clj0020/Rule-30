package com.carson.rule30.utils

import kotlin.random.Random.Default.nextInt

/**
 * Shuffles an array randomly
 */
fun <T> Array<T>.shuffle(): Array<T> {
    for (index in this.indices) {
        val randomIndex = nextInt(this.size)

        // Swap with the random position
        val temp = this[index]
        this[index] = this[randomIndex]
        this[randomIndex] = temp
    }

    return this
}

/**
 * Shuffles a 2d array randomly
 */
fun <T> Array<Array<T>>.shuffle(): Array<Array<T>> {
    for (index in this.indices) {
        this[index] = this[index].shuffle()

        val randomIndex = nextInt(this.size)

        // Swap with the random position
        val temp = this[index]
        this[index] = this[randomIndex]
        this[randomIndex] = temp
    }

    return this
}