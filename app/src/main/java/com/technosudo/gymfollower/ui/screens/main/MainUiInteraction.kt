package com.technosudo.gymfollower.ui.screens.main

import com.technosudo.gymfollower.data.ExerciseData

interface MainUiInteraction {

    fun setNormalMode()
    fun setEditMode()
    fun updateExercises()
    fun deleteExercise(exerciseData: ExerciseData)
    fun setNameDialogVisible()
    fun setNameDialogInvisible()
    fun onTextChange(text: String)
    fun setName(text: String)
    fun selectExercise(exercise: Pair<Int, ExerciseData>)
    fun increaseWeight()
    fun decreaseWeight()

    companion object {
        fun default(viewModel: MainViewModel) = object : MainUiInteraction {
            override fun setNormalMode() {
                viewModel.setNormalMode()
            }
            override fun setEditMode() {
                viewModel.setEditMode()
            }

            override fun updateExercises() {
                viewModel.updateExercises()
            }

            override fun deleteExercise(exerciseData: ExerciseData) {
                viewModel.deleteExercise(exerciseData)
            }

            override fun setNameDialogVisible() {
                viewModel.setNameDialogVisible()
            }
            override fun setNameDialogInvisible() {
                viewModel.setNameDialogInvisible()
            }

            override fun onTextChange(text: String) {
                viewModel.onTextChange(text = text)
            }

            override fun setName(text: String) {
                viewModel.setName(
                    text = text
                )
            }

            override fun selectExercise(exercise: Pair<Int, ExerciseData>) {
                viewModel.selectExercise(exercise)
            }

            override fun increaseWeight() {
                viewModel.increaseWeight()
            }

            override fun decreaseWeight() {
                viewModel.decreaseWeight()
            }
        }
    }
}