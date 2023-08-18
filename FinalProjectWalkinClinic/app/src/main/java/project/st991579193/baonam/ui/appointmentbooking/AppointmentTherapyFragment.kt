package project.st991579193.baonam.ui.appointmentbooking

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import project.st991579193.baonam.MainActivity
import project.st991579193.baonam.R
import project.st991579193.baonam.databinding.FragmentAppointmentTherapyBinding
import project.st991579193.baonam.room.ClinicApplication

class AppointmentTherapyFragment : Fragment() {
    private var _binding: FragmentAppointmentTherapyBinding? = null

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
        _binding = FragmentAppointmentTherapyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        root.findViewById<Button>(R.id.btnTherapySubmit).setOnClickListener {
            appointmentBookingViewModel.newTherapy.height = root.findViewById<EditText>(R.id.edHeight).text.toString().toFloat();
            appointmentBookingViewModel.newTherapy.weight = root.findViewById<EditText>(R.id.edWeight).text.toString().toFloat();
            appointmentBookingViewModel.newTherapy.visitReason = root.findViewById<TextInputEditText>(R.id.edReasons).text.toString();
            appointmentBookingViewModel.newTherapy.howLongExperience = root.findViewById<TextInputEditText>(R.id.edHowLongExperience).text.toString();
            appointmentBookingViewModel.newTherapy.expectations = root.findViewById<TextInputEditText>(R.id.edExpectations).text.toString();
            appointmentBookingViewModel.newTherapy.haveInsurance = root.findViewById<CheckBox>(R.id.cbInsurance).isChecked;

            appointmentBookingViewModel.submitTherapyApt(appointmentBookingViewModel.newUser, appointmentBookingViewModel.newTherapy);

            root.findViewById<EditText>(R.id.edHeight).text?.clear();
            root.findViewById<EditText>(R.id.edWeight).text?.clear();
            root.findViewById<TextInputEditText>(R.id.edReasons).text?.clear();
            root.findViewById<TextInputEditText>(R.id.edHowLongExperience).text?.clear();
            root.findViewById<TextInputEditText>(R.id.edExpectations).text?.clear();
            root.findViewById<CheckBox>(R.id.cbInsurance).isChecked = false;
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}