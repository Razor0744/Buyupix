package team.four.mys.domain.usecases

import org.junit.Assert
import org.junit.Test

class GetCurrencyIconUseCaseTest {

    @Test
    fun shouldReturnPriceString() {
        val useCase = GetCurrencyIconUseCase()
        val actual = useCase.execute(currency = "USD")
        val expected = "$"

        Assert.assertEquals(expected, actual)
    }
}