package project.st991579193.baonam.ui.management.recycleviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import project.st991579193.baonam.R
import project.st991579193.baonam.room.Therapy
import project.st991579193.baonam.room.User

class TherapyRecycleView(private val sampleList:List<Therapy>): RecyclerView.Adapter<TherapyRecycleView.TherapyRecycleViewHolder>(){

    public val selectedTherapy = MutableLiveData<Therapy>();

    class TherapyRecycleViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val lineOne: TextView = itemView.findViewById<TextView>(R.id.lineOne)
        val lineTwo: TextView = itemView.findViewById<TextView>(R.id.lineTwo)
        val lineThree: TextView = itemView.findViewById<TextView>(R.id.lineThree)
        val lineFour: TextView = itemView.findViewById<TextView>(R.id.lineFour)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TherapyRecycleViewHolder {
        val userView = LayoutInflater.from(parent.context).inflate(R.layout.list_apt, parent, false)

        return TherapyRecycleViewHolder(userView)
    }

    override fun onBindViewHolder(holder: TherapyRecycleViewHolder, position: Int) {
        val currentItem = sampleList[position]
        var insurance = ""
        insurance = if(currentItem.haveInsurance==true){
            "Yes"
        }else {
            "No"
        }
        holder.lineOne.text = "Patient ID:${currentItem.uid}"
        holder.lineTwo.text = "Reason to visit: ${currentItem.visitReason}"
        holder.lineThree.text = "Experiencing Period: ${currentItem.howLongExperience}"
        holder.lineFour.text = "Expectation: ${currentItem.expectations}"

        holder.itemView.setOnClickListener {
            this.selectedTherapy.value = currentItem;
        }
    }

    override fun getItemCount() = sampleList.size

}