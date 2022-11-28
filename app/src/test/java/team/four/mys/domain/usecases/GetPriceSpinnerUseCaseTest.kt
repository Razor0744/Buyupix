package team.four.mys.domain.usecases

import org.junit.Assert
import org.junit.Test

class GetPriceSpinnerUseCaseTest {

    @Test
    fun shouldReturnPriceString() {
        val useCase = GetPriceSpinnerUseCase()
        val actual = useCase.execute(item = "USD")
        val expected = "$"

        Assert.assertEquals(expected, actual)
    }
}