package team.four.mys.domain.usecases

import org.junit.Test
import team.four.mys.domain.models.Valute

class GetCurrenciesUseCaseTest {

    @Test
    suspend fun test() {
        val valute: Valute? = GetCurrenciesUseCase().execute()
        assert(valute != null)
    }
}