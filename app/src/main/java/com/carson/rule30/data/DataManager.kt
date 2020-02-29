package com.carson.rule30.data

import com.carson.rule30.data.models.World
import com.carson.rule30.data.models.WorldPoint
import io.reactivex.Single

interface DataManager {

    fun executeRule30(world: World?): Single<World>

    fun executeRule30FromCenterOut(world: World?): Single<World>

    fun shuffleWorld(world: World?): Single<World>

    fun randomlyGenerateWorld(numGenerations: Int, numElements: Int, percentageChanceOne: Int): Single<World>

    fun extractPointsFromWorld(world: World?): Single<List<WorldPoint>>

    fun extractPointsFromWorld(world: World?, radius: Float): Single<List<WorldPoint>>

}