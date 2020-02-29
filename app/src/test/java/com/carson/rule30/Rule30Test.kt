package com.carson.rule30

import com.carson.rule30.data.models.World
import com.carson.rule30.data.Rule30
import org.junit.Test

class Rule30Test {

    /**
     * Test every type of pattern result in rule 30. Just use arrays with 3 elements
     *
     * Make sure that the middle value of every result matches the rule.
     *
     * 000 111 110 101 100 010 001 011
     *  0   0   0   0   1   1   1   1
     */
    @Test
    fun testRule30() {
        // Zero Results

        // First pattern 000 should be 0
        var initialArray = arrayOf(0, 0, 0)
        var resultArray = Rule30.rule30(initialArray)
        assert(resultArray[1] == 0)

        // Second pattern 111 should be 0
        initialArray = arrayOf(1, 1, 1)
        resultArray = Rule30.rule30(initialArray)
        assert(resultArray[1] == 0)

        // Third pattern 110 should be 0
        initialArray = arrayOf(1, 1, 0)
        resultArray = Rule30.rule30(initialArray)
        assert(resultArray[1] == 0)

        // Fourth pattern 101 should be 0
        initialArray = arrayOf(1, 0, 1)
        resultArray = Rule30.rule30(initialArray)
        assert(resultArray[1] == 0)

        // One results

        // Fifth pattern 100 should be 1
        initialArray = arrayOf(1, 0, 0)
        resultArray = Rule30.rule30(initialArray)
        assert(resultArray[1] == 1)

        // Sixth pattern 010 should be 1
        initialArray = arrayOf(0, 1, 0)
        resultArray = Rule30.rule30(initialArray)
        assert(resultArray[1] == 1)

        // Seventh pattern 001 should be 1
        initialArray = arrayOf(0, 0, 1)
        resultArray = Rule30.rule30(initialArray)
        assert(resultArray[1] == 1)

        // Last pattern 011 should be 1
        initialArray = arrayOf(0, 1, 1)
        resultArray = Rule30.rule30(initialArray)
        assert(resultArray[1] == 1)
    }

    /**
     * Basic rule 30 with a 2x3 table as input, test for starting row of every pattern.
     */
    @Test
    fun testRule30World2By3() {
        val world = World(numGenerations = 2, numElements = 3)
        world.generate()

        // First initial array 000, next generation should be 000
        world.worldTiles[0] = arrayOf(0, 0, 0)
        var resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[1].contentEquals(arrayOf(0, 0, 0)))

        // Second initial array 111, next generation should be 101
        world.worldTiles[0] = arrayOf(1, 1, 1)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 1, 1)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 1)))

        // Third initial array 110, next generation should be 100
        world.worldTiles[0] = arrayOf(1, 1, 0)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 0)))

        // Forth initial array 101, next generation should be 101
        world.worldTiles[0] = arrayOf(1, 0, 1)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 1)))

        // Fifth initial array 100, next generation should be 110
        world.worldTiles[0] = arrayOf(1, 0, 0)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[1].contentEquals(arrayOf(1, 1, 0)))

        // Sixth initial array 010, next generation should be 010
        world.worldTiles[0] = arrayOf(0, 1, 0)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 0)))

        // Seventh initial array 001, next generation should be 011
        world.worldTiles[0] = arrayOf(0, 0, 1)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 0, 1)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 1)))

        // Eighth initial array 011, next generation should be 011
        world.worldTiles[0] = arrayOf(0, 1, 1)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 1)))
    }

    /**
     * Basic rule 30 with a 3x3 world as input, test for starting row of every pattern.
     */
    @Test
    fun testRule30World3By3() {
        val world = World(numGenerations = 3, numElements = 3)
        world.generate()

        // First initial array 000, next generation should be 000, third generation should be 000
        world.worldTiles[0] = arrayOf(0, 0, 0)
        var resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[1].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[2].contentEquals(arrayOf(0, 0, 0)))

        // Second initial array 111, next generation should be 101, third generation should be 101
        world.worldTiles[0] = arrayOf(1, 1, 1)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 1, 1)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[2].contentEquals(arrayOf(1, 0, 1)))

        // Third initial array 110, next generation should be 100, third generation should be 110
        world.worldTiles[0] = arrayOf(1, 1, 0)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[2].contentEquals(arrayOf(1, 1, 0)))

        // Forth initial array 101, next generation should be 101, third generation should be 101
        world.worldTiles[0] = arrayOf(1, 0, 1)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[2].contentEquals(arrayOf(1, 0, 1)))

        // Fifth initial array 100, next generation should be 110, third generation should be 100
        world.worldTiles[0] = arrayOf(1, 0, 0)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[1].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[2].contentEquals(arrayOf(1, 0, 0)))

        // Sixth initial array 010, next generation should be 010, third generation should be 010
        world.worldTiles[0] = arrayOf(0, 1, 0)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[2].contentEquals(arrayOf(0, 1, 0)))

        // Seventh initial array 001, next generation should be 011, third generation should be 011
        world.worldTiles[0] = arrayOf(0, 0, 1)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 0, 1)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[2].contentEquals(arrayOf(0, 1, 1)))

        // Eighth initial array 011, next generation should be 011, third generation should be 011
        world.worldTiles[0] = arrayOf(0, 1, 1)
        resultTable = Rule30.rule30(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[2].contentEquals(arrayOf(0, 1, 1)))
    }

    /**
     * Rule 30 from center out with a 3 by 3 world.
     */
    @Test
    fun testRule30FromCenter3By3() {
        val world = World(numGenerations = 3, numElements = 3)
        world.generate()

        // First initial array 000, top should be 000, bottom should be 000
        world.worldTiles[1] = arrayOf(0, 0, 0)
        var resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[1].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[2].contentEquals(arrayOf(0, 0, 0)))

        // Second initial array 111, top should be 101, bottom should be 101
        world.worldTiles[1] = arrayOf(1, 1, 1)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[1].contentEquals(arrayOf(1, 1, 1)))
        assert(resultTable[2].contentEquals(arrayOf(1, 0, 1)))

        // Third initial array 110, top should be 100, bottom should be 100
        world.worldTiles[1] = arrayOf(1, 1, 0)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[1].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[2].contentEquals(arrayOf(1, 0, 0)))

        // Fourth initial array 101, top should be 101, bottom should be 101
        world.worldTiles[1] = arrayOf(1, 0, 1)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[2].contentEquals(arrayOf(1, 0, 1)))

        // Fifth initial array 100, top should be 110, bottom should be 110
        world.worldTiles[1] = arrayOf(1, 0, 0)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[2].contentEquals(arrayOf(1, 1, 0)))

        // Sixth initial array 010, top should be 010, bottom should be 010
        world.worldTiles[1] = arrayOf(0, 1, 0)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[2].contentEquals(arrayOf(0, 1, 0)))

        // Seventh initial array 001, top should be 011, bottom should be 011
        world.worldTiles[1] = arrayOf(0, 0, 1)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[1].contentEquals(arrayOf(0, 0, 1)))
        assert(resultTable[2].contentEquals(arrayOf(0, 1, 1)))

        // Fourth initial array 011, top should be 011, bottom should be 011
        world.worldTiles[1] = arrayOf(0, 1, 1)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[2].contentEquals(arrayOf(0, 1, 1)))
    }

    /**
     * Rule 30 from center out with a 9 by 3 world.
     */
    @Test
    fun testRule30FromCenter9By3() {
        val world = World(numGenerations = 9, numElements = 3)
        world.generate()

        // First initial array 000
        world.worldTiles[4] = arrayOf(0, 0, 0)
        var resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[1].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[2].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[3].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[4].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[5].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[6].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[7].contentEquals(arrayOf(0, 0, 0)))
        assert(resultTable[8].contentEquals(arrayOf(0, 0, 0)))

        // Second initial array 111
        world.worldTiles[4] = arrayOf(1, 1, 1)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[2].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[3].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[4].contentEquals(arrayOf(1, 1, 1)))
        assert(resultTable[5].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[6].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[7].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[8].contentEquals(arrayOf(1, 0, 1)))

        // Third initial array 110
        world.worldTiles[4] = arrayOf(1, 1, 0)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[2].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[3].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[4].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[5].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[6].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[7].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[8].contentEquals(arrayOf(1, 1, 0)))

        // Fourth initial array 101
        world.worldTiles[4] = arrayOf(1, 0, 1)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[1].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[2].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[3].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[4].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[5].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[6].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[7].contentEquals(arrayOf(1, 0, 1)))
        assert(resultTable[8].contentEquals(arrayOf(1, 0, 1)))

        // Fifth initial array 100
        world.worldTiles[4] = arrayOf(1, 0, 0)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[1].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[2].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[3].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[4].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[5].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[6].contentEquals(arrayOf(1, 0, 0)))
        assert(resultTable[7].contentEquals(arrayOf(1, 1, 0)))
        assert(resultTable[8].contentEquals(arrayOf(1, 0, 0)))

        // Sixth initial array 010
        world.worldTiles[4] = arrayOf(0, 1, 0)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[2].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[3].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[4].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[5].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[6].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[7].contentEquals(arrayOf(0, 1, 0)))
        assert(resultTable[8].contentEquals(arrayOf(0, 1, 0)))

        // Seventh initial array 001
        world.worldTiles[4] = arrayOf(0, 0, 1)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[2].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[3].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[4].contentEquals(arrayOf(0, 0, 1)))
        assert(resultTable[5].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[6].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[7].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[8].contentEquals(arrayOf(0, 1, 1)))

        // Eighth initial array 011
        world.worldTiles[4] = arrayOf(0, 1, 1)
        resultTable = Rule30.rule30FromCenterOut(world).worldTiles
        assert(resultTable[0].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[1].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[2].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[3].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[4].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[5].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[6].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[7].contentEquals(arrayOf(0, 1, 1)))
        assert(resultTable[8].contentEquals(arrayOf(0, 1, 1)))
    }

}