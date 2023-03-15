package team.four.mys.domain.usecases

class CategoryOfSubscriptionUseCase {

    fun execute(name: String): String {
        var category = ""
        when (name) {
            "Apple One" -> category = "Other"
            "Discord Nitro" -> category = "Gaming"
            "Dr.Web" -> category = "Defence"
            "Dropbox" -> category = "Cloud"
            "Flo" -> category = "Other"
            "Google Drive" -> category = "Cloud"
            "Kaspersky" -> category = "Defence"
            "Kion" -> category = "Movies"
            "MTC Premium" -> category = "Other"
            "IVI" -> category = "Movies"
            "Megogo" -> category = "Movies"
            "Microsoft 365" -> category = "Other"
            "MyBook" -> category = "Books"
            "Netflix" -> category = "Movies"
            "NordVPN" -> category = "Other"
            "Ozon Premium" -> category = "Other"
            "PornHub" -> category = "Other"
            "Premier One" -> category = "Movies"
            "Pure" -> category = "Other"
            "Spotify" -> category = "Music"
            "Start" -> category = "Movies"
            "Tinkoff Pro" -> category = "Other"
            "VK Combo" -> category = "Other"
            "YouTube" -> category = "Movies"
            "Амедиатека" -> category = "Movies"
            "ЛитРес" -> category = "Books"
            "СберПрайм" -> category = "Other"
            else -> category = "Other"
        }
        return category
    }
}