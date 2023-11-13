package team.four.mys.domain.repository

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.data.room.Subscription

interface FirebaseRepository {

    fun getUID(): String

    suspend fun getData(uid: String): DocumentSnapshot?

    fun setData(uid: String, list: List<Subscription>)

    suspend fun getNumberSync(uid: String): DocumentSnapshot?

    fun setNumberSync(uid: String, number: Int)

    fun checkUser(): Boolean
}