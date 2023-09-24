package team.four.mys.domain.usecases

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.RoomRepository

class GetSubscriptionInfoUseCaseTest {

    private val roomRepository = mock<RoomRepository>()

    @Test
    fun shouldReturnSubscription() {
        val model = Subscription(
            id = 2,
            name = "Spotify",
            icon = "Any",
            price = "55",
            currency = "USD",
            currencyIcon = "$",
            date = 17,
            reminder = true,
            category = "Gaming"
        )

        Mockito.`when`(roomRepository.getSubscriptionInfo(2)).thenReturn(model)

        val useCase = GetSubscriptionInfoUseCase(roomRepository = roomRepository)
        val expected = model
        val actual = useCase.execute(2)

        Assert.assertEquals(expected, actual)
    }
}