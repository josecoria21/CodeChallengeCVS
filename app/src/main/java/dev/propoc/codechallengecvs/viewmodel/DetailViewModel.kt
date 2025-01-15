package dev.propoc.codechallengecvs.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

class DetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun convertDate(dateString: String) {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        try {
            val parsedDate = ZonedDateTime.parse(dateString, formatter)
            val outFormat = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.getDefault())
            val formattedDate = parsedDate.format(outFormat)

            _uiState.update { currentState ->
                currentState.copy(convertedDate = formattedDate, errorMessage = null)
            }
        } catch (e: DateTimeParseException) {
            e.printStackTrace()
            _uiState.update { currentState ->
                currentState.copy(convertedDate = null, errorMessage = "Invalid date format")
            }
        }
    }
}

data class DetailUiState(
    val convertedDate: String? = null,
    val errorMessage: String? = null
)
