package team.four.buyupix.domain.usecases

class GetUrlImageUseCase {

    fun execute(name: String): String {
        var url = ""
        when (name) {
            "Spotify" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Spotify.png?alt=media&token=89cd172f-201d-4a5e-acc6-e0da3344c26e"
            }
            "VK Combo" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/VK%20Kombo.png?alt=media&token=edc2d633-0e2b-4610-9fc0-301552bc679b"
            }
            "Netflix" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Netfix.png?alt=media&token=769f754e-3a71-44c1-ac59-86e59c7ef412"
            }
            "Apple Music" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Apple%20Music.png?alt=media&token=c4b5b8d5-4af6-4095-a332-2375daa55b8d"
            }
        }
        return url
    }
}