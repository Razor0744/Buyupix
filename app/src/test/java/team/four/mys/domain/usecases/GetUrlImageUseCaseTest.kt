package team.four.mys.domain.usecases

import org.junit.Assert
import org.junit.Test

class GetUrlImageUseCaseTest {

    @Test
    fun shouldReturnUrlImage() {
        val actual = GetUrlImageUseCase().execute(name = "Spotify")
        val expected =
            "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FSpotify.png?alt=media&token=1fd2ee8a-4215-4820-9438-6ced8eacdfd9"

        Assert.assertEquals(expected, actual)
    }
}