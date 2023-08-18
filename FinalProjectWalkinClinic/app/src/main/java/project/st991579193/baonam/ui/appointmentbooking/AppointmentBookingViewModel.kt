package project.st991579193.baonam.ui.appointmentbooking

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import project.st991579193.baonam.room.*

class AppointmentBookingViewModel(
    private val userDao: UserDao,
    private val therapyDao:TherapyDao,
    private val vaccineDao:VaccineDao
    ) : ViewModel() {
    val allUser: LiveData<List<User>> = userDao.getAll().asLiveData()
    val allTherapy: LiveData<List<Therapy>> = therapyDao.getAll().asLiveData()
    val allVaccine: LiveData<List<Vaccine>> = vaccineDao.getAll().asLiveData()

    var newUser = User()
    var newTherapy = Therapy()
    var newVaccine = Vaccine()

    private fun insertUser(user: User) : Int {
        return userDao.insertAll(user).toInt();
    }
    private fun insertVaccine(vaccine: Vaccine) {
        viewModelScope.launch {
            vaccineDao.insertAll(vaccine);
        }
    }
    private fun insertTherapy(therapy: Therapy) {
        viewModelScope.launch {
            therapyDao.insertAll(therapy);
        }
    }
    private fun getTherapyByUser(uid: Int) : Therapy{
        return therapyDao.getTherapyByUser(uid);
    }
    private fun getVaccineByUser(uid: Int) :Vaccine{
        return vaccineDao.getVaccineByUser(uid);
    }
    private fun getUserByEmail(email: String) : Int {
        val user = userDao.getUserByEmail(email);
        return if(user == null){
            -1;
        } else{
            userDao.getUserByEmail(email).uid;
        }
    }
    fun deleteVaccine(vaccine:Vaccine){
        vaccineDao.delete(vaccine);
    }
    fun deleteTherapy(therapy:Therapy){
        therapyDao.delete(therapy);
    }
    fun deleteUser(user:User){
        userDao.delete(user);
    }
    fun submitVaccineApt(newUser:User, newVaccine:Vaccine){
        newVaccine.uid = insertUser(newUser);
        insertVaccine(newVaccine);
    }
    fun submitTherapyApt(newUser:User, newTherapy:Therapy){
        newTherapy.uid = insertUser(newUser);
        insertTherapy(newTherapy);
    }
    fun getAptByEmail(email: String) : String{
        val uid = getUserByEmail(email);
        if(uid == -1){
            return ""
        }
        var result = "";
        Log.w("",uid.toString());
        val therapy = getTherapyByUser(uid);
        val vaccine = getVaccineByUser(uid);
        result = if (therapy==null){
            "";
        } else{
            therapy.toString();
        }
        result += if (vaccine==null){
            "";
        } else{
            vaccine.toString();
        }
        return result;
    }
}
class AppointmentBookingViewModelFactory(private val userDao: UserDao,
                                         private val therapyDao:TherapyDao,
                                         private val vaccineDao:VaccineDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppointmentBookingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppointmentBookingViewModel(userDao, therapyDao, vaccineDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}