package team.four.mys.data.repository

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.data.database.FirebaseDatabase
import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl(private val firebaseDatabase: FirebaseDatabase) : FirebaseRepository {

    override fun getUID(): String {
        return firebaseDatabase.getUID()
    }

    override suspend fun getData(uid: String): DocumentSnapshot? {
        return firebaseDatabase.getData(uid = uid)
    }

    override fun setData(uid: String, list: List<Subscription>) {
        firebaseDatabase.setData(uid = uid, list = list)
    }

    override suspend fun getNumberSync(uid: String): DocumentSnapshot? {
        return firebaseDatabase.getNumberSync(uid = uid)
    }

    override fun setNumberSync(uid: String, number: Int) {
        firebaseDatabase.setNumberSync(uid = uid, number = number)
    }

    override fun checkUser(): Boolean {
        return firebaseDatabase.checkUser()
    }

}