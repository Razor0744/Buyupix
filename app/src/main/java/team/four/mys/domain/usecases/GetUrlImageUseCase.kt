package team.four.mys.domain.usecases

class GetUrlImageUseCase {

    //27
    fun execute(name: String): String {
        val url: String
        when (name) {
            "Apple One" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FApple%20One.png?alt=media&token=cb68c6bf-04ff-4638-9c2a-74513fbeb3d3"
            "Discord Nitro" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FDiscord%20Nitro.png?alt=media&token=9cace1d4-7166-4a27-a9f5-1ecd4d4b5944"
            "Dr.Web" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FDr.Web.png?alt=media&token=6cba8581-ff03-4667-b119-5ca7b8b3ee6a"
            "Dropbox" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FDropbox.png?alt=media&token=3e8a44e3-7d90-4af5-99f2-9268d329568a"
            "Flo" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FFlo.png?alt=media&token=cfc990cb-e3c5-4734-ab3a-d24cdab99e27"
            "Google Drive" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FGoogle%20Drive.png?alt=media&token=c2047b71-1f59-4db1-81d0-464ed7e3548a"
            "Kaspersky" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FKaspersky.png?alt=media&token=a96316f3-c8e8-4aac-940e-a32870b38eb6"
            "Kion" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FKion.png?alt=media&token=826115c5-2aa7-45b5-84d9-2009de86e3f6"
            "MTC Premium" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FMTC%20Premium.png?alt=media&token=c71acb96-92ee-464e-bd3b-45ee6c1ada6c"
            "IVI" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FMask%20Group.png?alt=media&token=0bf44615-e2f3-46b1-b298-cfe8d783c012"
            "Megogo" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FMegogo.png?alt=media&token=d590a1bd-317a-46a0-b789-d919bf7b0745"
            "Microsoft 365" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FMicrosoft%20365.png?alt=media&token=c5cd88ee-0d9a-465a-af2e-cbd0bbf09c14"
            "MyBook" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FMyBook.png?alt=media&token=22a96eed-5500-495a-b908-78b70c5ede79"
            "Netflix" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FNetflix.png?alt=media&token=e1edee09-904e-415f-abf9-f12f73c13603"
            "NordVpn" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FNordVPN.png?alt=media&token=a5598906-9b4a-44e9-b94d-1f4ab16d9992"
            "Ozon Premium" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FOzon%20Premium.png?alt=media&token=ab2ebf4d-49f0-4cde-a17d-f290096dd167"
            "PornHub" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FPornHub.png?alt=media&token=4d7d4efb-8289-4e10-a66e-60dd57bfc5e1"
            "Premier One" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FPremier%20One.png?alt=media&token=cc8b657b-6dd7-4b1f-b41e-05725cf3a11b"
            "Pure" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FPure.png?alt=media&token=9d0746b7-27bd-48ee-86de-da991c233d06"
            "Spotify" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FSpotify.png?alt=media&token=1fd2ee8a-4215-4820-9438-6ced8eacdfd9"
            "Start" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FStart.png?alt=media&token=93f2749c-5b2e-46a0-add9-5a47125b604a"
            "Tinkoff Pro" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FTinkoff%20Pro.png?alt=media&token=04f9198b-37b2-4268-ba95-a67da9a653bf"
            "Vk Combo" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FVK%20Combo.png?alt=media&token=23334eb5-311e-4c95-a83c-b62080646242"
            "YouTube" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2FYouTube.png?alt=media&token=bb1ac4fa-2a06-4848-b751-51a5153e3cee"
            "Амедиотека" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2F%D0%90%D0%BC%D0%B5%D0%B4%D0%B8%D0%B0%D1%82%D0%B5%D0%BA%D0%B0.png?alt=media&token=96111329-193a-4cfc-b97a-069de0007a6e"
            "ЛитРес" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2F%D0%9B%D0%B8%D1%82%D0%A0%D0%B5%D1%81.png?alt=media&token=01717f7e-d464-45c3-b16f-b2a8289971c7"
            "СберПрайм" -> url =
                "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Logo%20Sub%2F%D0%A1%D0%B1%D0%B5%D1%80%D0%9F%D1%80%D0%B0%D0%B9%D0%BC.png?alt=media&token=021862b8-4c4c-4c6e-90b1-ed4a532c024b"
            else -> {
                url = "notImage"
            }
        }
        return url
    }
}