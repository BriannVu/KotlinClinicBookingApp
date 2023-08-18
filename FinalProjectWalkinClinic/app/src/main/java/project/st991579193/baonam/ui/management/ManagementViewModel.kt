package project.st991579193.baonam.ui.management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import project.st991579193.baonam.ui.appointmentbooking.AppointmentBookingViewModel

class ManagementViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is management Fragment for clinic administrator"
    }
    val text: LiveData<String> = _text

    private val _aptList = MutableLiveData<List<AppointmentBookingViewModel>>().apply {
        value = null;
    }
    val aptList: LiveData<List<AppointmentBookingViewModel>> = _aptList
}