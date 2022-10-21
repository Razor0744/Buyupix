package team.four.mys.domain.usecases

import com.google.firebase.auth.FirebaseAuth

class GetUIDUseCase {

     fun execute(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }
}