package com.redbox.survey.ui.results

import com.redbox.survey.domain.Test

sealed class ResultsState {
    object Initial : ResultsState()
    class Loaded(val test: Test) : ResultsState()
}