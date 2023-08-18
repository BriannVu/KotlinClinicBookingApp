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
    tableName = "vaccine"
)
data class Vaccine(
    @PrimaryKey(autoGenerate = true) var vid: Int,
    @ColumnInfo(name = "uid") var uid: Int,
    @ColumnInfo(name="vaccine_type") var vaccineType:String?,
    @ColumnInfo(name="number_of_vaccine") var numberOfVaccine:String?,
    @ColumnInfo(name="is_diagnosed_covid") var isDiagnosedCovid:Boolean?,
    @ColumnInfo(name="have_symptoms") var haveSymptoms:Boolean?
){
    constructor() : this(
        0,
        0,
        "",
        "",
        false,
        false
    )
    override fun toString(): String {
        return "Uid: $uid\nVaccine Type: $vaccineType\nVaccine Shots: ${numberOfVaccine.toString()}\nIs Diagnosed Covid: ${isDiagnosedCovid.toString()}\nSymptoms: ${haveSymptoms.toString()}"
    }
}
