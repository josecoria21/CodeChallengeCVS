package dev.propoc.codechallengecvs.viewmodel

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel()
    }

    @Test
    fun `convertDate should correctly format valid ISO date`() = runTest {
        // Given a valid ISO date string
        val validDate = "2025-01-16T10:15:30+01:00"

        // When convertDate is called
        detailViewModel.convertDate(validDate)

        // Then the UI state should contain the correctly formatted date and no error message
        val uiState = detailViewModel.uiState.first()
        assertEquals("16 Jan, 2025", uiState.convertedDate)
        assertNull(uiState.errorMessage)
    }

    @Test
    fun `convertDate should set error for invalid ISO date`() = runTest {
        // Given an invalid ISO date string
        val invalidDate = "invalid-date"

        // When convertDate is called
        detailViewModel.convertDate(invalidDate)

        // Then the UI state should contain an error message and null for the converted date
        val uiState = detailViewModel.uiState.first()
        assertEquals("Invalid date format", uiState.errorMessage)
        assertNull(uiState.convertedDate)
    }

    @Test
    fun `convertDate should handle empty date string`() = runTest {
        // Given an empty date string
        val emptyDate = ""

        // When convertDate is called
        detailViewModel.convertDate(emptyDate)

        // Then the UI state should contain an error message and null for the converted date
        val uiState = detailViewModel.uiState.first()
        assertEquals("Invalid date format", uiState.errorMessage)
        assertNull(uiState.convertedDate)
    }
}
