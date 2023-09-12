package team.four.mys.data.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Firebase : FirebaseDatabase {

    private val db = Firebase.firestore
    private val user = FirebaseAuth.getInstance().currentUser

    override fun getUID(): String {
        return user?.uid.toString()
    }

    override fun synchronization() {
        TODO("Not yet implemented")
    }

    override fun checkUser(): Boolean {
        val checkUser = Firebase.auth.currentUser
        return checkUser != null
    }


}