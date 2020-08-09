package com.redbox.survey.presentation.results

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.survey.domain.Test

class ResultsViewModel : ViewModel() {
    var state = MutableLiveData<ResultsState>()

    init {
        state.value = ResultsState.Initial
    }

    fun setResults(test: Test) {
        state.postValue(ResultsState.Loaded(test))
    }
}