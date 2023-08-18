package project.st991579193.baonam.ui.management

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.st991579193.baonam.MainActivity
import project.st991579193.baonam.R
import project.st991579193.baonam.databinding.FragmentAppointmentListBinding
import project.st991579193.baonam.ui.appointmentbooking.AppointmentBookingViewModel
import project.st991579193.baonam.ui.appointmentbooking.AppointmentBookingViewModelFactory
import project.st991579193.baonam.ui.management.recycleviews.TherapyRecycleView
import project.st991579193.baonam.ui.management.recycleviews.VaccineRecycleView


class AppointmentListFragment : Fragment() {
    private var _binding: FragmentAppointmentListBinding? = null

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

        _binding = FragmentAppointmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        appointmentBookingViewModel.allTherapy.observe(viewLifecycleOwner){
                items ->
            val recyclerView : RecyclerView = root.findViewById(R.id.rvAptTherapy)
            recyclerView.layoutManager = LinearLayoutManager(root.context)
            val therapyRecycleView = TherapyRecycleView(items)
            therapyRecycleView.selectedTherapy.observe(viewLifecycleOwner){ item ->
                val builder = AlertDialog.Builder(this.context)

                builder.setTitle("Confirm")
                builder.setMessage("${item.toString()}\n\nAre you sure to delete this therapy?")

                builder.setPositiveButton("YES") { dialog, which ->
                    appointmentBookingViewModel.deleteTherapy(item)
                    dialog.dismiss()
                }

                builder.setNegativeButton(
                    "NO"
                ) { dialog, which ->
                    dialog.dismiss()
                }

                builder.create().show()
            }
            recyclerView.adapter = therapyRecycleView
            recyclerView.setHasFixedSize(true)
        }
        appointmentBookingViewModel.allVaccine.observe(viewLifecycleOwner){
                items ->
            val recyclerView : RecyclerView = root.findViewById(R.id.rvAptVaccine)
            recyclerView.layoutManager = LinearLayoutManager(root.context)
            val vaccineRecycleView = VaccineRecycleView(items)
            vaccineRecycleView.selectedVaccine.observe(viewLifecycleOwner){ item ->
                val builder = AlertDialog.Builder(this.context)

                builder.setTitle("Confirm")
                builder.setMessage("${item.toString()}\n\nAre you sure to delete this vaccine?")

                builder.setPositiveButton("YES") { dialog, which ->
                    appointmentBookingViewModel.deleteVaccine(item)
                    dialog.dismiss()
                }

                builder.setNegativeButton(
                    "NO"
                ) { dialog, which ->
                    dialog.dismiss()
                }

                builder.create().show()
            }
            recyclerView.adapter = vaccineRecycleView
            recyclerView.setHasFixedSize(true)

        }
        root.findViewById<Button>(R.id.buttonTherapy).setOnClickListener{
            root.findViewById<RecyclerView>(R.id.rvAptVaccine).visibility = RecyclerView.INVISIBLE
            root.findViewById<RecyclerView>(R.id.rvAptTherapy).visibility = RecyclerView.VISIBLE
        }
        root.findViewById<Button>(R.id.buttonVaccine).setOnClickListener{
            root.findViewById<RecyclerView>(R.id.rvAptVaccine).visibility = RecyclerView.VISIBLE
            root.findViewById<RecyclerView>(R.id.rvAptTherapy).visibility = RecyclerView.INVISIBLE
        }
        val btnVaccine = root.findViewById<Button>(R.id.buttonVaccine);


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}