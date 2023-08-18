package project.st991579193.baonam.ui.management

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.st991579193.baonam.MainActivity
import project.st991579193.baonam.R
import project.st991579193.baonam.databinding.*
import project.st991579193.baonam.ui.appointmentbooking.AppointmentBookingViewModel
import project.st991579193.baonam.ui.appointmentbooking.AppointmentBookingViewModelFactory
import project.st991579193.baonam.ui.management.recycleviews.TherapyRecycleView
import project.st991579193.baonam.ui.management.recycleviews.UserRecycleView

class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val appointmentBookingViewModel: AppointmentBookingViewModel by activityViewModels {
        AppointmentBookingViewModelFactory(
            (activity as MainActivity).database
                .userDao(),
            (activity as MainActivity).database
                .therapyDao(),
            (activity as MainActivity).database
                .vaccineDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        appointmentBookingViewModel.text.observe(viewLifecycleOwner) {
//
//        }

        appointmentBookingViewModel.allUser.observe(viewLifecycleOwner){
                items ->
            val recyclerView : RecyclerView = root.findViewById(R.id.rvUser)
            recyclerView.layoutManager = LinearLayoutManager(root.context)
            val userRecycleView = UserRecycleView(items)
            userRecycleView.selectedUser.observe(viewLifecycleOwner){ item ->
                val builder = AlertDialog.Builder(this.context)

                builder.setTitle("Confirm")
                builder.setMessage("${item.toString()}\n\nAre you sure to delete this user?")

                builder.setPositiveButton("YES") { dialog, which ->
                    appointmentBookingViewModel.deleteUser(item)
                    dialog.dismiss()
                }

                builder.setNegativeButton(
                    "NO"
                ) { dialog, which ->
                    dialog.dismiss()
                }

                builder.create().show()
            }
            recyclerView.adapter = userRecycleView
            recyclerView.setHasFixedSize(true)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}