package project.st991579193.baonam.ui.appointmentbooking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import project.st991579193.baonam.MainActivity
import project.st991579193.baonam.R
import project.st991579193.baonam.databinding.FragmentAppointmentBookingBinding
import project.st991579193.baonam.room.ClinicApplication
import project.st991579193.baonam.room.User

class AppointmentBookingFragment : Fragment() {

    private var _binding: FragmentAppointmentBookingBinding? = null

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
        _binding = FragmentAppointmentBookingBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.btnNextTherapy.setOnClickListener {
            updateUserInformation(appointmentBookingViewModel, root);
            view?.findNavController()?.navigate(R.id.action_nav_appointment_booking_to_nav_appointment_therapy);
        }
        binding.btnNextVaccine.setOnClickListener {
            updateUserInformation(appointmentBookingViewModel, root);
            view?.findNavController()?.navigate(R.id.action_nav_appointment_booking_to_nav_appointment_vaccine);
        }

        return root
    }

    private fun updateUserInformation(appointmentBookingViewModel:AppointmentBookingViewModel,root:View){
        appointmentBookingViewModel.newUser.fullName = root.findViewById<EditText>(R.id.edName).text.toString();
        appointmentBookingViewModel.newUser.gender = root.findViewById<EditText>(R.id.edGender).text.toString();
        appointmentBookingViewModel.newUser.dob = root.findViewById<EditText>(R.id.edDOB).text.toString();
        appointmentBookingViewModel.newUser.phone = root.findViewById<EditText>(R.id.edPhone).text.toString();
        appointmentBookingViewModel.newUser.address = root.findViewById<EditText>(R.id.edAddress).text.toString();
        appointmentBookingViewModel.newUser.email = root.findViewById<EditText>(R.id.edEmail).text.toString();
        appointmentBookingViewModel.newUser.availability = root.findViewById<EditText>(R.id.edAvailability).text.toString();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}