package team.four.mys.domain.repository

interface FirebaseRepository {

    fun getUID(): String

    fun checkUser(): Boolean
}