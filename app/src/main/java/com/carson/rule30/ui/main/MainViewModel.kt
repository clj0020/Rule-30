package com.carson.rule30.ui.main

import androidx.lifecycle.MutableLiveData
import com.carson.rule30.data.DataManager
import com.carson.rule30.data.models.World
import com.carson.rule30.data.models.WorldPoint
import com.carson.rule30.ui.base.BaseViewModel
import com.carson.rule30.utils.SchedulerProvider
import io.reactivex.Single
import kotlin.math.roundToInt

class MainViewModel(dataManager: DataManager,
                    schedulerProvider: SchedulerProvider): BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    var worldLiveData: MutableLiveData<World> = MutableLiveData()
    val pointsLiveData: MutableLiveData<List<WorldPoint>> = MutableLiveData()

    /**
     * Executes rule 30 on the current world. Posts to the relevant live data.
     */
    fun executeRule30() {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.executeRule30(worldLiveData.value)
                .subscribeOn(schedulerProvider.io())
                .flatMap { world ->
                    worldLiveData.postValue(world)
                    dataManager.extractPointsFromWorld(world)
                }
                .observeOn(schedulerProvider.ui())
                .subscribe({ points ->
                    pointsLiveData.postValue(points)
                    setIsLoading(false)
                }, { throwable ->
                    navigator?.handleError(throwable)
                    setIsLoading(false)
                })
        )
    }

    /**
     * Executes rule 30 from center out on the current world. Posts to the relevant live data.
     */
    fun executeRule30CenterOut() {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.executeRule30FromCenterOut(worldLiveData.value)
                .subscribeOn(schedulerProvider.io())
                .flatMap { world ->
                    worldLiveData.postValue(world)
                    dataManager.extractPointsFromWorld(world)
                }
                .observeOn(schedulerProvider.ui())
                .subscribe({ points ->
                    pointsLiveData.postValue(points)
                    setIsLoading(false)
                }, { throwable ->
                    navigator?.handleError(throwable)
                    setIsLoading(false)
                })
        )
    }

    /**
     * Shuffles the world randomly.
     */
    fun shuffleWorld() {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.shuffleWorld(worldLiveData.value)
                .subscribeOn(schedulerProvider.io())
                .flatMap { world ->
                    worldLiveData.postValue(world)
                    dataManager.extractPointsFromWorld(world)
                }
                .observeOn(schedulerProvider.ui())
                .subscribe({ points ->
                    pointsLiveData.postValue(points)
                    setIsLoading(false)
                }, { throwable ->
                    navigator?.handleError(throwable)
                    setIsLoading(false)
                })
        )
    }

    /**
     * Randomly generates a new world.
     *
     * @param width - The width of the world to create (height will be half).
     */
    fun randomlyGenerateWorld(width: Int) {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.randomlyGenerateWorld(numGenerations = width / 2, numElements = width, percentageChanceOne = 20)
                .flatMap { world ->
                    worldLiveData.postValue(world)
                    dataManager.extractPointsFromWorld(world)
                }
                .observeOn(schedulerProvider.ui())
                .subscribe({ points ->
                    pointsLiveData.postValue(points)
                    setIsLoading(false)
                }, { throwable ->
                    navigator?.handleError(throwable)
                    setIsLoading(false)
                })
        )

    }

    /**
     * Resets the world to a single point.
     *
     * @param width - The width of the world to create (height will be half).
     */
    fun resetWorld(width: Int) {
        setIsLoading(true)
        compositeDisposable.add(
            Single.fromCallable {
                    createInitialWorld(width)
                }
                .subscribeOn(schedulerProvider.io())
                .flatMap { world ->
                    worldLiveData.postValue(world)
                    dataManager.extractPointsFromWorld(world, 10f)
                }
                .observeOn(schedulerProvider.ui())
                .subscribe({ points ->
                    pointsLiveData.postValue(points)
                    setIsLoading(false)
                }, { throwable ->
                    navigator?.handleError(throwable)
                    setIsLoading(false)
                })
        )
    }

    /**
     * Shows the menu.
     */
    fun showMenu() {
        navigator?.showMenu()
    }

    /**
     * Resets the camera view.
     */
    fun resetCamera() {
        navigator?.resetCamera()
    }

    /**
     * Creates the initial world.
     *
     * @param width - The width of the world to create (height will be half).
     *
     * @return World - a world with a single point in the center of the first generation.
     */
    private fun createInitialWorld(width: Int): World {
        val initialWorld = World(numGenerations = width / 2, numElements = width)
        initialWorld.generate()

        val centerElementIndex = ((initialWorld.numElements - 1) / 2.0).roundToInt()

        initialWorld.worldTiles[0][centerElementIndex] = 1

        return initialWorld
    }
}