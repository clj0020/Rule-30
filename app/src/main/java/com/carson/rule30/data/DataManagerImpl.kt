package com.carson.rule30.data

import android.content.Context
import com.carson.rule30.R
import com.carson.rule30.data.models.World
import com.carson.rule30.data.models.WorldPoint
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManagerImpl @Inject constructor(private val context: Context) : DataManager {


    /**
     * Execute Rule 30 on a world
     *
     * @param world - the world to execute on.
     *
     * @return Single<World> - a single containing an updated world instance.
     */
    override fun executeRule30(world: World?): Single<World> {
        world?.let {
            return Single.fromCallable {
                Rule30.rule30(it)
            }
        } ?: run {
            return Single.error(Exception(context.getString(R.string.error_no_world)))
        }
    }

    /**
     * Execute Rule 30 from the center out on the world.
     *
     * @param world - the world to execute on.
     *
     * @return Single<World> - a single containing an updated world instance.
     */
    override fun executeRule30FromCenterOut(world: World?): Single<World> {
        world?.let {
            return Single.fromCallable {
                Rule30.rule30FromCenterOut(it)
            }
        } ?: run {
            return Single.error(Exception(context.getString(R.string.error_no_world)))
        }
    }

    /**
     * Shuffle the world.
     *
     * @param world - the world to execute on.
     *
     * @return Single<World> - a single containing an updated world instance.
     */
    override fun shuffleWorld(world: World?): Single<World> {
        world?.let {
            return Single.fromCallable {
                // Shuffle the world.
                world.shuffle()
                return@fromCallable world
            }
        } ?: run {
            return Single.error(Exception(context.getString(R.string.error_no_world)))
        }
    }

    /**
     * Randomly generates a world.
     *
     * @param numGenerations - The number of generations in the world.
     * @param numElements - The number of elements per generation.
     * @param percentageChanceOne - The percentage change that an element will be one.
     *
     * @return Single<World> - a single containing a randomly generated world.
     */
    override fun randomlyGenerateWorld(numGenerations: Int,
                                       numElements: Int,
                                       percentageChanceOne: Int): Single<World> {
        return Single.fromCallable {
            val world = World(numGenerations = numGenerations, numElements = numElements)
            world.generateRandom(percentageChanceOne)
            world
        }
    }

    /**
     * Extracts a list of points from the inputted world.
     *
     * @param world - The world who's points you want to extract.
     *
     * @return Single<List<WorldPoint>> that represents the world's world tiles as world points.
     */
    override fun extractPointsFromWorld(world: World?): Single<List<WorldPoint>> {
        world?.let {
            return Single.fromCallable {
                val points = arrayListOf<WorldPoint>()
                for (x in world.worldTiles.indices) {
                    for (y in world.worldTiles[x].indices) {
                        val value = world.worldTiles[x][y]
                        if (value == 1) {
                            points.add(
                                WorldPoint(
                                    y.toFloat(),
                                    x.toFloat()
                                )
                            )
                        }
                    }
                }
                points
            }
        } ?: run {
            return Single.error(Exception(context.getString(R.string.error_no_world)))
        }
    }

    /**
     * Extracts a list of points from the inputted world.
     *
     * @param world - The world who's points you want to extract.
     * @param radius - The radius of each point.
     *
     * @return Single<List<WorldPoint>> that represents the world's world tiles as world points.
     */
    override fun extractPointsFromWorld(world: World?, radius: Float): Single<List<WorldPoint>> {
        world?.let {
            return Single.fromCallable {
                val points = arrayListOf<WorldPoint>()
                for (x in world.worldTiles.indices) {
                    for (y in world.worldTiles[x].indices) {
                        val value = world.worldTiles[x][y]
                        if (value == 1) {
                            points.add(
                                WorldPoint(
                                    y.toFloat(),
                                    x.toFloat(),
                                    radius
                                )
                            )
                        }
                    }
                }
                points
            }
        } ?: run {
            return Single.error(Exception(context.getString(R.string.error_no_world)))
        }
    }

}