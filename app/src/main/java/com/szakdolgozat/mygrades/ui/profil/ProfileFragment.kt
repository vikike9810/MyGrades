package com.szakdolgozat.mygrades.ui.profil

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_profil.*
import java.util.*

class ProfileFragment : Fragment(), ProfileView {

    var Profil_Name :TextView?=null
    var Profil_EditName: EditText?=null
    var Profil_EditBday: EditText?=null
    var Profil_Bday: TextView?=null
    var Profil_Email: TextView?=null
    var Profil_EditEmail: EditText?=null
    var Profil_city: TextView?=null
    var Profil_Editcity: EditText?=null
    var Profil_Zip: TextView?=null
    var Profil_EditZip: EditText?=null
    var Profil_Street: TextView?=null
    var Profil_EditStreet: EditText?=null
    var Profil_number: TextView?=null
    var Profil_Editnumber: EditText?=null

    lateinit var profilePresenter: ProfilePresenter
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profilePresenter= ProfilePresenter(this)
        mainActivity= activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.activity_profil, container, false)
        bindListeners(view)
        loadProfile()
        return view
    }

    fun bindListeners(v: View){
        Profil_Name=v?.findViewById<TextView>(R.id.Profil_Name)
        Profil_EditName=v?.findViewById<EditText>(R.id.Profil_EditName)
        Profil_Name?.setOnClickListener{
            onClickName(it)
        }
        Profil_Bday=v?.findViewById<TextView>(R.id.Profil_Bday)
        v?.findViewById<TextView>(R.id.Profil_Bday)?.setOnClickListener{
            onClickBirthDay(it)
        }
        Profil_Email=v?.findViewById<TextView>(R.id.Profil_Email)
        Profil_EditEmail=v?.findViewById<EditText>(R.id.Profil_EditEmail)
        v?.findViewById<TextView>(R.id.Profil_Email)?.setOnClickListener{
            onClickEmail(it)
        }
        Profil_city=v?.findViewById<TextView>(R.id.Profil_city)
        Profil_Editcity=v?.findViewById<EditText>(R.id.Profil_Editcity)
        Profil_city?.setOnClickListener{
            onClickcity(it)
        }
        Profil_Street=v?.findViewById<TextView>(R.id.Profil_Street)
        Profil_EditStreet=v?.findViewById<EditText>(R.id.Profil_EditStreet)
        Profil_Street?.setOnClickListener{
            onClickStreet(it)
        }
        Profil_Zip=v?.findViewById<TextView>(R.id.Profil_Zip)
        Profil_EditZip=v?.findViewById<EditText>(R.id.Profil_EditZip)
        Profil_Zip?.setOnClickListener{
            onClickZip(it)
        }
        Profil_number=v?.findViewById<TextView>(R.id.Profil_number)
        Profil_Editnumber=v?.findViewById<EditText>(R.id.Profil_Editnumber)
        Profil_number?.setOnClickListener{
            onClicknumber(it)
        }
        val floatingActionButton=v?.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton?.setOnClickListener{
            saveProfile(it)
        }
    }

    fun onClickName(v: View){
        setTextesToVisible()
        v.visibility=View.INVISIBLE
        Profil_EditName?.visibility=View.VISIBLE
    }

    fun onClickEmail(v: View){
        setTextesToVisible()
        v.visibility=View.INVISIBLE
        Profil_EditEmail?.visibility=View.VISIBLE
        Profil_EditEmail?.setSelection(Profil_EditEmail?.length()?:0)
    }

    fun onClickcity(v: View){
        setTextesToVisible()
        v.visibility=View.INVISIBLE
        Profil_Editcity?.visibility=View.VISIBLE
        Profil_Editcity?.setSelection(Profil_Editcity?.length()?:0)
    }

    fun onClickStreet(v: View){
        setTextesToVisible()
        v.visibility=View.INVISIBLE
        Profil_EditStreet?.visibility=View.VISIBLE
        Profil_EditStreet?.setSelection(Profil_EditStreet?.length()?:0)
    }

    fun onClickZip(v: View){
        setTextesToVisible()
        v.visibility=View.INVISIBLE
        Profil_EditZip?.visibility=View.VISIBLE
        Profil_EditZip?.setSelection(Profil_EditZip?.length()?:0)
    }

    fun onClicknumber(v: View){
        setTextesToVisible()
        v.visibility=View.INVISIBLE
        Profil_Editnumber?.visibility=View.VISIBLE
        Profil_Editnumber?.setSelection(Profil_Editnumber?.length()?:0)
    }


    fun setTextesToVisible(){
        Profil_Name?.visibility=View.VISIBLE
        Profil_Name?.text=Profil_EditName?.text

        Profil_Email?.visibility=View.VISIBLE
        Profil_Email?.text=Profil_EditEmail?.text

        Profil_city?.visibility=View.VISIBLE
        Profil_city?.text=Profil_Editcity?.text

        Profil_Street?.visibility=View.VISIBLE
        Profil_Street?.text=Profil_EditStreet?.text

        Profil_Zip?.visibility=View.VISIBLE
        Profil_Zip?.text=Profil_EditZip?.text

        Profil_number?.visibility=View.VISIBLE
        Profil_number?.text=Profil_Editnumber?.text

        Profil_EditName?.visibility=View.INVISIBLE
        Profil_EditEmail?.visibility=View.INVISIBLE
        Profil_Editcity?.visibility=View.INVISIBLE
        Profil_EditStreet?.visibility=View.INVISIBLE
        Profil_EditZip?.visibility=View.INVISIBLE
        Profil_Editnumber?.visibility=View.INVISIBLE
    }

    fun onClickBirthDay(v :View){
        DatePickerDialog(
            mainActivity,
            DatePickerDialog.OnDateSetListener(){view, year, month, dayOfMonth ->
                val real_month=month+1
                Profil_Bday?.text= "$year.$real_month.$dayOfMonth"
            },
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DATE
        ).show()
    }

    fun saveProfile(v: View){
        setTextesToVisible()
        setProfile()
        profilePresenter.SaveProfile()
    }


    private fun setProfile(){
        User.Name=Profil_Name?.text.toString()
        User.birthday= Profil_Bday?.text.toString()
        User.email=Profil_Email?.text.toString()
        User.address.city=Profil_city?.text.toString()
        User.address.street=Profil_Street?.text.toString()
        User.address.zip=Integer.parseInt(Profil_Zip?.text.toString())
        User.address.number=Profil_number?.text.toString()
    }

    fun loadProfile(){
        Profil_Name?.text=User.Name
        Profil_Bday?.text=User.birthday
        Profil_Email?.text=User.email
        Profil_city?.text=User.address.city
        Profil_Street?.text=User.address.street
        Profil_number?.text=User.address.number
    }

    override fun dataSaveOK(){
        Toast.makeText(mainActivity, "Saved",Toast.LENGTH_SHORT).show()
    }
    override fun  dataSaveError(message :String){
        Toast.makeText(mainActivity, message,Toast.LENGTH_SHORT).show()
    }
}

