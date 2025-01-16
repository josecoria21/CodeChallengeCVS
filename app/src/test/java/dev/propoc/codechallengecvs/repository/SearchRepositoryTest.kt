package dev.propoc.codechallengecvs.repository

import app.cash.turbine.test
import dev.propoc.codechallengecvs.model.ImageResponseModel
import dev.propoc.codechallengecvs.model.Item
import dev.propoc.codechallengecvs.model.Media
import dev.propoc.codechallengecvs.network.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchRepositoryTest {

    private lateinit var repository: SearchRepository
    private val mockApiService: ApiService = mockk()

    @Before
    fun setUp() {
        repository = SearchRepository(mockApiService)
    }

    @Test
    fun `search should emit a list of items from ApiService`() = runBlocking {
        val mockResponse = mockResponse
        coEvery { mockApiService.fetchData(name = "test") } returns mockResponse

        // Test the repository
        repository.search("test").test {
            val result = awaitItem()
            assertEquals(2, result.size)
            assertEquals("Sample Item 1", result[0].title)
            assertEquals("https://example.com/item1", result[0].link)
            assertEquals("https://example.com/media.jpg", result[0].media.m)
            assertEquals("This is a sample description for Item 1.", result[0].description)
            assertEquals("tag1 tag2 tag3", result[0].tags)
            awaitComplete()
        }
    }
}

val mockMedia = Media(m = "https://example.com/media.jpg")

val mockItems = listOf(
    Item(
        title = "Sample Item 1",
        link = "https://example.com/item1",
        media = mockMedia,
        dateTaken = "2023-12-15T10:15:30Z",
        description = "This is a sample description for Item 1.",
        published = "2023-12-16T12:00:00Z",
        author = "Author Name 1",
        authorId = "authorId1",
        tags = "tag1 tag2 tag3"
    ),
    Item(
        title = "Sample Item 2",
        link = "https://example.com/item2",
        media = Media(m = "https://example.com/media2.jpg"),
        dateTaken = "2023-12-20T08:00:00Z",
        description = "This is a sample description for Item 2.",
        published = "2023-12-21T14:30:00Z",
        author = "Author Name 2",
        authorId = "authorId2",
        tags = "tag4 tag5"
    )
)

val mockResponse = ImageResponseModel(
    title = "Sample Response Title",
    link = "https://example.com",
    description = "This is a sample response description.",
    modified = "2023-12-22T16:00:00Z",
    generator = "Sample Generator",
    items = mockItems
)
