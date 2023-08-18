package project.st991579193.baonam.ui.management

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import project.st991579193.baonam.R
import project.st991579193.baonam.databinding.FragmentAppointmentTherapyBinding
import project.st991579193.baonam.databinding.FragmentAppointmentVaccineBinding
import project.st991579193.baonam.databinding.FragmentManagementListBinding

class ManagementListFragment : Fragment() {
    private var _binding: FragmentManagementListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val appointmentBookingViewModel =
            ViewModelProvider(this).get(ManagementViewModel::class.java)

        _binding = FragmentManagementListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        appointmentBookingViewModel.text.observe(viewLifecycleOwner) {

        }
        root.findViewById<Button>(R.id.usersBtn).setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_nav_management_list_to_nav_management_user_list);
        }
        root.findViewById<Button>(R.id.aptsBtn).setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_nav_management_list_to_nav_management_appointment_list);
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}