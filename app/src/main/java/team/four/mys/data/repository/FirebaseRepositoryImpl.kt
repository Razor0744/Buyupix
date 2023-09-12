package team.four.mys.data.repository

import team.four.mys.data.database.FirebaseDatabase
import team.four.mys.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl(private val firebaseDatabase: FirebaseDatabase) : FirebaseRepository {

    override fun getUID(): String {
        return firebaseDatabase.getUID()
    }

    override fun checkUser(): Boolean {
        return firebaseDatabase.checkUser()
    }

}