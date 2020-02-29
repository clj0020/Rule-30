package com.carson.rule30.data

import com.carson.rule30.data.models.World
import kotlin.math.roundToInt

object Rule30 {

    /**
     * Runs Rule 30 on an array.
     *
     * 000 111  110  101  100  010  001  011
     *  0   0    0    0    1    1    1    1
     *
     *  @param initialArray - the initial array to run rule 30 on.
     *
     *  @return Array<Int> that represents the initial array after rule 30 is applied.
     */
    fun rule30(initialArray: Array<Int>): Array<Int> {
        val change = initialArray.copyOf()

        var x = 0
        while (x + 1 < initialArray.size - 1) {
            val left = initialArray[x]
            val middle = initialArray[x + 1]
            val right = initialArray[x + 2]

            if (left == 0 && middle == 0 && right == 0 ||
                left == 1 && middle == 1 && right == 1 ||
                left == 1 && middle == 1 && right == 0 ||
                left == 1 && middle == 0 && right == 1) {
                change[x + 1] = 0
            } else {
                change[x + 1] = 1
            }
            x++
        }

        return change
    }

    /**
     * Runs Rule 30 on a world object.
     *
     * 000 111  110  101  100  010  001  011
     *  0   0    0    0    1    1    1    1
     *
     *  @param world - the initial world that rule 30 will run on.
     *
     *  @return World that represents the initial world after rule 30 is applied.
     */
    fun rule30(world: World): World {
        val resultWorld = World(world.numGenerations, world.numElements)
        resultWorld.worldTiles = world.worldTiles.map { it.clone() }.toTypedArray()

        for (currentGeneration in 0..world.numGenerations-2) {
            resultWorld.worldTiles[currentGeneration + 1] =
                rule30(resultWorld.worldTiles[currentGeneration])
        }

        return resultWorld
    }

    /**
     * Runs Rule 30 starting from the center on a world object.
     *
     * 000 111  110  101  100  010  001  011
     *  0   0    0    0    1    1    1    1
     *
     *  @param world - the initial world that rule 30 will run on.
     *
     *  @return World that represents the initial world after rule 30 is applied from the center out.
     */
    fun rule30FromCenterOut(world: World): World {
        val centerIndex = ((world.numGenerations - 1) / 2.0).roundToInt()

        val resultWorld = World(world.numGenerations, world.numElements)
        resultWorld.worldTiles = world.worldTiles.map { it.clone() }.toTypedArray()

        // Iterate center down.
        for (currentGeneration in centerIndex..world.numGenerations-2) {
            resultWorld.worldTiles[currentGeneration + 1] =
                rule30(resultWorld.worldTiles[currentGeneration])
        }

        // Iterate center up.
        for (currentGeneration in centerIndex downTo 1) {
            resultWorld.worldTiles[currentGeneration - 1] =
                rule30(resultWorld.worldTiles[currentGeneration])
        }

        return resultWorld
    }

}