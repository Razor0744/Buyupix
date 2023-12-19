package team.four.mys.data.repository

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.data.database.FirebaseDatabase
import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(private val firebaseDatabase: FirebaseDatabase) : FirebaseRepository {

    override fun getUID(): String {
        return firebaseDatabase.getUID()
    }

    override suspend fun getData(uid: String): DocumentSnapshot? {
        return firebaseDatabase.getData(uid = uid)
    }

    override fun setData(uid: String, list: List<Subscription>) {
        firebaseDatabase.setData(uid = uid, list = list)
    }

    override suspend fun getTimeSync(uid: String): String? {
        return firebaseDatabase.getTimeSync(uid = uid)
    }

    override fun setTimeSync(uid: String, time: String) {
        firebaseDatabase.setTimeSync(uid = uid, time = time)
    }

    override fun checkUser(): Boolean {
        return firebaseDatabase.checkUser()
    }

}