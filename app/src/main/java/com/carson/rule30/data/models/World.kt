package com.carson.rule30.data.models

import com.carson.rule30.utils.shuffle
import kotlin.math.roundToInt
import kotlin.random.Random.Default.nextInt

class World(val numGenerations: Int = 256, val numElements: Int = 256) {

    lateinit var worldTiles: Array<Array<Int>>

    /**
     * Generate a world full of zeros.
     */
    fun generate() {
        worldTiles = Array(numGenerations) { Array(numElements) { 0 } }
    }

    /**
     * Generates a world with ones randomly chosen
     */
    fun generateRandom() {
        worldTiles = Array(numGenerations) { Array(numElements) { nextInt(2) /** randomly choose 0 or 1 */} }
    }

    /**
     * Generates a world with ones randomly chosen at a percentage.
     *
     * @param percentageChanceOne - The percentage chance that one will be chosen.
     */
    fun generateRandom(percentageChanceOne: Int) {
        worldTiles = Array(numGenerations) {
            Array(numElements) {
                val value = nextInt(100)
                if (value < percentageChanceOne) {
                    1
                } else {
                    0
                }
            }
        }
    }

    /**
     * Shuffle the world.
     */
    fun shuffle() {
        this.worldTiles = this.worldTiles.shuffle()
    }

}
