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

    init {
        resetWorld()
    }

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
     */
    fun randomlyGenerateWorld() {
        setIsLoading(true)
        compositeDisposable.add(
            dataManager.randomlyGenerateWorld(500, 1000, 20)
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
     */
    fun resetWorld() {
        setIsLoading(true)
        compositeDisposable.add(
            Single.fromCallable {
                    createInitialWorld()
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
     * @return World - a world with a single point in the center of the first generation.
     */
    private fun createInitialWorld(): World {
        val initialWorld = World(numGenerations = 500, numElements = 1000)
        initialWorld.generate()

        val centerElementIndex = ((initialWorld.numElements - 1) / 2.0).roundToInt()

        initialWorld.worldTiles[0][centerElementIndex] = 1

        return initialWorld
    }

}