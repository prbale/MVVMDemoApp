package app.bale.demoapplication.ui.dealList

import app.bale.demoapplication.data.model.Deal

/**
 * Sealed class to handled UI states
 */
sealed class DealsUiState {
    class Success(val data: List<Deal>?): DealsUiState()
    class Error(val errorMessage: String): DealsUiState()
    object Loading: DealsUiState()
}