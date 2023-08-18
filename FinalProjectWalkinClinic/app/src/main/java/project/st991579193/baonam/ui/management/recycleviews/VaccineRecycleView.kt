package project.st991579193.baonam.ui.management.recycleviews

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import project.st991579193.baonam.R
import project.st991579193.baonam.room.Therapy
import project.st991579193.baonam.room.User
import project.st991579193.baonam.room.Vaccine
import java.util.concurrent.Flow

class VaccineRecycleView(private val sampleList:List<Vaccine>): RecyclerView.Adapter<VaccineRecycleView.VaccineRecycleViewHolder>(){
    public val selectedVaccine = MutableLiveData<Vaccine>();

    class VaccineRecycleViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val lineOne: TextView = itemView.findViewById<TextView>(R.id.lineOne)
        val lineTwo: TextView = itemView.findViewById<TextView>(R.id.lineTwo)
        val lineThree: TextView = itemView.findViewById<TextView>(R.id.lineThree)
        val lineFour: TextView = itemView.findViewById<TextView>(R.id.lineFour)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineRecycleViewHolder {
        val userView = LayoutInflater.from(parent.context).inflate(R.layout.list_apt, parent, false)
        return VaccineRecycleViewHolder(userView)
    }

    override fun onBindViewHolder(holder: VaccineRecycleViewHolder, position: Int) {
        val currentItem = sampleList[position]
        holder.lineOne.text = "Patient ID:${currentItem.uid}"
        holder.lineTwo.text = "Vaccine Type: ${currentItem.vaccineType}"
        holder.lineThree.text = "Taken Shots: ${currentItem.numberOfVaccine} | Covid symptoms: ${if(currentItem.haveSymptoms==true) "Yes" else "No"}"
        holder.lineFour.text = "Is diagnosed with Covid before: ${if(currentItem.isDiagnosedCovid==true) "Yes" else "No"}"
        holder.itemView.setOnClickListener {
            this.selectedVaccine.value = currentItem;
        }
    }

    override fun getItemCount() = sampleList.size

}