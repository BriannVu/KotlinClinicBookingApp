package project.st991579193.baonam.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        childColumns = ["uid"],
        parentColumns = ["uid"])],
    tableName = "therapy"
)
data class Therapy(
    @PrimaryKey(autoGenerate = true) var tid: Int,
    @ColumnInfo(name = "uid") var uid: Int,
    @ColumnInfo(name="height") var height:Float,
    @ColumnInfo(name="weight") var weight:Float,
    @ColumnInfo(name="visit_reason") var visitReason:String?,
    @ColumnInfo(name="how_long_experience") var howLongExperience:String?,
    @ColumnInfo(name="expectations") var expectations:String?,
    @ColumnInfo(name="have_insurance") var haveInsurance:Boolean?
){
    constructor() : this(
        0,
        0,
        -1F,
        -1F,
        "",
        "",
        "",
        false
    )
    override fun toString(): String {
        return "Uid: $uid\nHeight: ${height.toString()} | Weight: ${weight.toString()}\nVisit Reason: $visitReason\nExperiencing Period: $howLongExperience\nExpectation: $expectations\nInsurance: ${haveInsurance.toString()}\n"
    }
}
