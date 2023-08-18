package project.st991579193.baonam.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name="full_name") var fullName:String?,
    @ColumnInfo(name="gender") var gender:String?,
    @ColumnInfo(name="dob") var dob:String?,
    @ColumnInfo(name="phone") var phone:String?,
    @ColumnInfo(name="address") var address:String?,
    @ColumnInfo(name="email") var email:String?,
    @ColumnInfo(name="availability") var availability:String?
){
    constructor() : this(
        0,
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
    override fun toString(): String {
         return "Uid: $uid\nFull Name: $fullName\nGender: $gender\nDob: $dob\nPhone: $phone\nAddress: $address\nEmail: $email\nAvailability: $availability"
    }
}

