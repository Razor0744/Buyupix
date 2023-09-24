package team.four.mys.domain.usecases

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import team.four.mys.domain.repository.FirebaseRepository

class CheckUserUseCaseTest {

    private val firebaseRepository = mock<FirebaseRepository>()

    @Test
    fun shouldReturnTrue() {

        Mockito.`when`(firebaseRepository.checkUser()).thenReturn(true)

        val useCase = CheckUserUseCase(firebaseRepository = firebaseRepository)
        val expected = true
        val actual = useCase.execute()

        Assert.assertEquals(expected, actual)
    }
}