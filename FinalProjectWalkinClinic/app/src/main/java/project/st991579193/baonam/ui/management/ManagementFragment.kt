package project.st991579193.baonam.ui.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import project.st991579193.baonam.MainActivity
import project.st991579193.baonam.R
import project.st991579193.baonam.databinding.FragmentManagementBinding
import project.st991579193.baonam.ui.appointmentbooking.AppointmentBookingViewModel
import project.st991579193.baonam.ui.appointmentbooking.AppointmentBookingViewModelFactory

class ManagementFragment : Fragment() {

    private var _binding: FragmentManagementBinding? = null

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

        _binding = FragmentManagementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        root.findViewById<Button>(R.id.login).setOnClickListener {
            if(root.findViewById<EditText>(R.id.username).text.toString()=="vu8@sheridancollege.ca"
                &&
                root.findViewById<EditText>(R.id.password).text.toString()=="admin"
            ){
                root.findViewById<EditText>(R.id.username).text.clear();
                root.findViewById<EditText>(R.id.password).text.clear();
                view?.findNavController()?.navigate(R.id.action_nav_management_to_nav_management_list);
            }
            else{
                Toast.makeText(this.context, "The input email and password are invalid!", Toast.LENGTH_LONG).show();
            }
        }

        root.findViewById<Button>(R.id.btnEmailConfirm).setOnClickListener {
            val text = root.findViewById<EditText>(R.id.etEmailAddress).text.toString().trim();
            if (text!=""){
                val displayContent = appointmentBookingViewModel.getAptByEmail(text);
                if (displayContent == ""){
                    Toast.makeText(this.context, "Cannot find your appointment!", Toast.LENGTH_LONG).show();
                }
                else{
                    root.findViewById<EditText>(R.id.etEmailAddress).text.clear();
                    Toast.makeText(this.context, displayContent, Toast.LENGTH_LONG).show();
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}