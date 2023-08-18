package project.st991579193.baonam.ui.appointmentbooking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import project.st991579193.baonam.MainActivity
import project.st991579193.baonam.R
import project.st991579193.baonam.databinding.FragmentAppointmentTherapyBinding
import project.st991579193.baonam.databinding.FragmentAppointmentVaccineBinding

class AppointmentVaccineFragment : Fragment() {
    private var _binding: FragmentAppointmentVaccineBinding? = null

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

        _binding = FragmentAppointmentVaccineBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        appointmentBookingViewModel.allUser.observe(viewLifecycleOwner) {
//
//        }
        root.findViewById<Button>(R.id.btnVaccineSubmit).setOnClickListener {
            val vaccineID = root.findViewById<RadioGroup>(R.id.rgVaccineType).checkedRadioButtonId;
            appointmentBookingViewModel.newVaccine.vaccineType = root.findViewById<RadioButton>(vaccineID).text.toString();

            val numberOfNumberID = root.findViewById<RadioGroup>(R.id.edNumberOfVaccine).checkedRadioButtonId;
            appointmentBookingViewModel.newVaccine.numberOfVaccine = root.findViewById<RadioButton>(numberOfNumberID).text.toString();

            appointmentBookingViewModel.newVaccine.isDiagnosedCovid = root.findViewById<CheckBox>(R.id.isDiagnosedCovid).isChecked;
            appointmentBookingViewModel.newVaccine.haveSymptoms = root.findViewById<CheckBox>(R.id.haveSymptoms).isChecked;

            appointmentBookingViewModel.submitVaccineApt(appointmentBookingViewModel.newUser,appointmentBookingViewModel.newVaccine)

            root.findViewById<RadioGroup>(R.id.rgVaccineType).clearCheck();
            root.findViewById<RadioGroup>(R.id.edNumberOfVaccine).clearCheck();
            root.findViewById<CheckBox>(R.id.isDiagnosedCovid).isChecked = false;
            root.findViewById<CheckBox>(R.id.haveSymptoms).isChecked = false;
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}