package team.four.mys

import adapters.CustomRecyclerAdapterSubscriptions
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.databinding.ActivitySubscriptionsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import models.Subscriptions


class SubscriptionsActivity : AppCompatActivity() {

    private lateinit var subscriptions: ArrayList<Subscriptions>
    private lateinit var adapter: CustomRecyclerAdapterSubscriptions
    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivitySubscriptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // all subscriptions
        subscriptions = arrayListOf()
        adapter = CustomRecyclerAdapterSubscriptions(this, subscriptions)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        fireStore()
    }


    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun fireStore() {
        db = FirebaseFirestore.getInstance()
        var i = 1
        while (i <= 31) {
            println(i)
            db.collection(uid()).document(i.toString()).collection("date")
                .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null) {
                            return
                        }

                        for (dc: DocumentChange in value?.documentChanges!!) {
                            if (dc.type == DocumentChange.Type.ADDED) {
                                subscriptions.add(dc.document.toObject(Subscriptions::class.java))
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }

                })

            db.collection(uid()).document(i.toString()).collection("noDate")
                .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null) {
                            return
                        }

                        for (dc: DocumentChange in value?.documentChanges!!) {
                            if (dc.type == DocumentChange.Type.ADDED) {
                                subscriptions.add(dc.document.toObject(Subscriptions::class.java))
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }

                })
            println(i)
            i++
        }
    }
}
