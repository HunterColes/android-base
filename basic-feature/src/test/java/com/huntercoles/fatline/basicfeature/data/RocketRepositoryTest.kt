package com.huntercoles.fatline.basicfeature.data

import com.huntercoles.fatline.basicfeature.data.local.dao.RocketDao
import com.huntercoles.fatline.basicfeature.data.mapper.toDomainModel
import com.huntercoles.fatline.basicfeature.data.mapper.toEntityModel
import com.huntercoles.fatline.basicfeature.data.remote.api.RocketApi
import com.huntercoles.fatline.basicfeature.data.repository.RocketRepositoryImpl
import com.huntercoles.fatline.basicfeature.domain.repository.RocketRepository
import com.huntercoles.fatline.basicfeature.generateTestRocketFromRemote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RocketRepositoryTest {

    @RelaxedMockK
    private lateinit var rocketApi: RocketApi

    @RelaxedMockK
    private lateinit var rocketDao: RocketDao

    private lateinit var objectUnderTest: RocketRepository

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        setUpRocketRepository()
    }

    @Test
    fun `should refresh rockets if local database is empty`() = runTest {
        // Given
        every { rocketDao.getRockets() } returns flowOf(emptyList())

        // When
        objectUnderTest.getRockets().collect()

        // Then
        coVerifyOrder {
            rocketApi.getRockets()
            rocketDao.saveRockets(any())
        }
    }

    @Test
    fun `should save mapped rockets locally if retrieved from remote`() = runTest {
        // Given
        val testRocketsFromRemote = listOf(generateTestRocketFromRemote())
        val testRocketsToCache = testRocketsFromRemote.map { it.toDomainModel().toEntityModel() }
        coEvery { rocketApi.getRockets() } returns testRocketsFromRemote

        // When
        objectUnderTest.refreshRockets()

        // Then
        coVerify { rocketDao.saveRockets(testRocketsToCache) }
    }

    private fun setUpRocketRepository() {
        objectUnderTest = RocketRepositoryImpl(
            rocketApi,
            rocketDao,
        )
    }
}
