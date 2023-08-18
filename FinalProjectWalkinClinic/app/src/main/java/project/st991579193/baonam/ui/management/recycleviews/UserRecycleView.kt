package project.st991579193.baonam.ui.management.recycleviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import project.st991579193.baonam.R
import project.st991579193.baonam.room.User
import project.st991579193.baonam.room.Vaccine

class UserRecycleView(private val sampleList:List<User>): RecyclerView.Adapter<UserRecycleView.UserRecycleViewHolder>(){
    public val selectedUser = MutableLiveData<User>();

    class UserRecycleViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val lineOne: TextView = itemView.findViewById<TextView>(R.id.lineOne)
        val lineTwo: TextView = itemView.findViewById<TextView>(R.id.lineTwo)
        val lineThree: TextView = itemView.findViewById<TextView>(R.id.lineThree)
        val lineFour: TextView = itemView.findViewById<TextView>(R.id.lineFour)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecycleViewHolder {
        val userView = LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)

        return UserRecycleViewHolder(userView)
    }

    override fun onBindViewHolder(holder: UserRecycleViewHolder, position: Int) {
        val currentItem = sampleList[position]
        holder.lineOne.text = "User Id: ${currentItem.uid} | ${currentItem.fullName} (${currentItem.gender}, ${currentItem.dob})"
        holder.lineTwo.text = "${currentItem.phone} | ${currentItem.email}"
        holder.lineThree.text = "${currentItem.address}"
        holder.lineFour.text = "${currentItem.availability}"
        holder.itemView.setOnClickListener {
            this.selectedUser.value = currentItem;
        }
    }

    override fun getItemCount() = sampleList.size

}