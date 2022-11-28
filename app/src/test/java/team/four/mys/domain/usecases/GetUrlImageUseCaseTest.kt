package team.four.mys.domain.usecases

import org.junit.Assert
import org.junit.Test

class GetUrlImageUseCaseTest {

    @Test
    fun shouldReturnUrlImage() {
        val actual = GetUrlImageUseCase().execute(name = "Spotify")
        val expected =
            "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Spotify.png?alt=media&token=89cd172f-201d-4a5e-acc6-e0da3344c26e"

        Assert.assertEquals(expected, actual)
    }
}