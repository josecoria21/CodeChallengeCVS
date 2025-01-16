package dev.propoc.codechallengecvs.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.propoc.codechallengecvs.model.Item
import dev.propoc.codechallengecvs.model.Media
import dev.propoc.codechallengecvs.repository.SearchRepository
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var repository: SearchRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set the main dispatcher for testing
        repository = mockk()
        searchViewModel = SearchViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher after tests
    }

    @Test
    fun `onQueryChanged should update the query state`() = runTest {
        val newQuery = "test query"
        searchViewModel.onQueryChanged(newQuery)
        assertEquals(newQuery, searchViewModel.query.value)
    }

    @Test
    fun `fetchData should update uiState with items on success`() = runTest {
        val query = "test"
        val mockItems = listOf(
            Item(
                title = "Sample Title 1",
                link = "https://example.com/1",
                media = Media(m = "https://example.com/media1.jpg"),
                dateTaken = "2025-01-01T12:00:00Z",
                description = "Sample Description 1",
                published = "2025-01-02T08:00:00Z",
                author = "Author 1",
                authorId = "author1",
                tags = "tag1 tag2"
            )
        )
        coEvery { repository.search(query) } returns flow { emit(mockItems) }

        searchViewModel.fetchData(query)

        val uiState = searchViewModel.uiState.value
        assertFalse(uiState.isLoading)
        assertEquals(mockItems, uiState.images)
        assertNull(uiState.errorMessage)
    }

    @Test
    fun `fetchData should update uiState with error message on failure`() = runTest {
        val query = "test"
        val errorMessage = "Something went wrong"
        coEvery { repository.search(query) } throws RuntimeException(errorMessage)

        searchViewModel.fetchData(query)

        val uiState = searchViewModel.uiState.value
        assertFalse(uiState.isLoading)
        assertTrue(uiState.images.isEmpty())
        assertEquals(errorMessage, uiState.errorMessage)
    }

    @Test
    fun `fetchData should set isLoading to true while loading`() = runTest {
        val query = "test"
        val mockItems = listOf(
            Item(
                title = "Sample Title",
                link = "https://example.com",
                media = Media(m = "https://example.com/media.jpg"),
                dateTaken = "2025-01-01T12:00:00Z",
                description = "Sample Description",
                published = "2025-01-02T08:00:00Z",
                author = "Author",
                authorId = "authorId",
                tags = "tag1 tag2"
            )
        )
        val flowResponse = MutableStateFlow(mockItems)

        coEvery { repository.search(query) } returns flowResponse

        searchViewModel.fetchData(query)

        val initialState = searchViewModel.uiState.value
        assertFalse(initialState.isLoading)

        flowResponse.emit(mockItems)

        val finalState = searchViewModel.uiState.value
        assertFalse(finalState.isLoading)
        assertEquals(mockItems, finalState.images)
    }
}

