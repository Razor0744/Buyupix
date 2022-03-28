package team.four.mys

import Adapters.CustomRecyclerAdapter
import Fragments.NavigationFragment
import Model.Subscription
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.databinding.ActivitySubscriptionsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class SubscriptionsActivity : AppCompatActivity() {

    private lateinit var subscriptions: ArrayList<Subscription>
    private lateinit var adapter: CustomRecyclerAdapter
    private lateinit var db : FirebaseFirestore
    private lateinit var binding: ActivitySubscriptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // all subscriptions
        subscriptions = arrayListOf()
        adapter = CustomRecyclerAdapter(this, subscriptions)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        fireStore()
        number()
    }


    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun fireStore() {
        db = FirebaseFirestore.getInstance()
        db.collection(uid()).document("Date").collection(uid())
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            subscriptions.add(dc.document.toObject(Subscription::class.java))
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

            })
    }

    private fun number(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = NavigationFragment()
        val bundle = Bundle()
        bundle.putInt("i", 1)
        fragment.arguments = bundle
        fragmentTransaction.add(R.id.fragment, fragment).commit()
    }
}
