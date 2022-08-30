package fragments

import adapters.CustomRecyclerAdapterSubscriptions
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import models.Subscriptions
import team.four.mys.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var subscriptions: ArrayList<Subscriptions>
    private lateinit var adapterSubscriptions: CustomRecyclerAdapterSubscriptions

    private lateinit var db : FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        subscriptions = arrayListOf()
        adapterSubscriptions = CustomRecyclerAdapterSubscriptions(requireContext(), subscriptions)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.adapter = adapterSubscriptions

        date()
        fireStore()

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun fireStore() {
        db = FirebaseFirestore.getInstance()
        db.collection(uid()).document("NoDate").collection(uid())
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            subscriptions.add(dc.document.toObject(Subscriptions::class.java))
                        }
                    }
                    adapterSubscriptions.notifyDataSetChanged()
                }

            })
    }

    private fun date() {
        val month: String = SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
        binding?.month?.text = month
    }
}