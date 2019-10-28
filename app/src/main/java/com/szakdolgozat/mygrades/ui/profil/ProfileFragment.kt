package com.szakdolgozat.mygrades.ui.profil

import android.app.Activity
import android.app.DatePickerDialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.util.CurrentDate
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.ui.main.MainActivity

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
    var Profil_Avatar: ImageView?=null
    var Profil_Avatar_Upload: ImageView?=null

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

    override fun getFragmentActivity():Activity{
       return activity as Activity
    }

    fun bindListeners(v: View){
        Profil_Avatar=v?.findViewById<ImageView>(R.id.Profil_Avatar)
        Profil_Avatar?.setOnClickListener{
            onClickAvatar(it)
        }

        Profil_Avatar_Upload=v?.findViewById<ImageView>(R.id.Profil_Avatar_Upload)
        Profil_Avatar_Upload?.setOnClickListener{
            onClickAvatar_Upload(it)
        }

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

    fun onClickAvatar(v: View){
        if(Profil_Avatar_Upload?.visibility==View.VISIBLE){
            Profil_Avatar_Upload?.visibility=View.INVISIBLE
        }
        else {
            setTextesDefault()
            Profil_Avatar_Upload?.visibility = View.VISIBLE
        }
    }

    fun onClickAvatar_Upload(v: View){
        profilePresenter.changeProfilePicture()
        setTextesDefault()
    }

    fun onClickName(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_EditName?.visibility=View.VISIBLE
    }

    fun onClickEmail(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_EditEmail?.visibility=View.VISIBLE
        Profil_EditEmail?.setSelection(Profil_EditEmail?.length()?:0)
    }

    fun onClickcity(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_Editcity?.visibility=View.VISIBLE
        Profil_Editcity?.setSelection(Profil_Editcity?.length()?:0)
    }

    fun onClickStreet(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_EditStreet?.visibility=View.VISIBLE
        Profil_EditStreet?.setSelection(Profil_EditStreet?.length()?:0)
    }

    fun onClickZip(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_EditZip?.visibility=View.VISIBLE
        Profil_EditZip?.setSelection(Profil_EditZip?.length()?:0)
    }

    fun onClicknumber(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_Editnumber?.visibility=View.VISIBLE
        Profil_Editnumber?.setSelection(Profil_Editnumber?.length()?:0)
    }


    fun setTextesDefault(){
        Profil_Avatar_Upload?.visibility=View.INVISIBLE

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
            CurrentDate.getYear(),
            (CurrentDate.getMonth()-1),
            CurrentDate.getDay()
        ).show()
    }

    fun saveProfile(v: View){
        setTextesDefault()
        setProfile()
        profilePresenter.SaveProfile()
    }

    override fun refreshAvatar() {
        Profil_Avatar?.setImageBitmap(User.avatar)
    }


    private fun setProfile(){
        User.Name=Profil_Name?.text.toString()
        User.birthday= Profil_Bday?.text.toString()
        User.email=Profil_Email?.text.toString()
        User.address.city=Profil_city?.text.toString()
        User.address.street=Profil_Street?.text.toString()
        User.address.zip=Profil_Zip?.text.toString()
        User.address.number=Profil_number?.text.toString()

    }

    fun loadProfile(){
        Profil_Avatar?.setImageBitmap(User.avatar?: (BitmapFactory.decodeResource(resources, R.drawable.profil)))
        Profil_Name?.text=User.Name
        Profil_Bday?.text=User.birthday
        Profil_Email?.text=User.email
        Profil_city?.text=User.address.city
        Profil_Street?.text=User.address.street
        Profil_number?.text=User.address.number

        Profil_EditName?.setText(User.Name)
        Profil_EditBday?.setText(User.birthday)
        Profil_EditEmail?.setText(User.email)
        Profil_Editcity?.setText(User.address.city)
        Profil_EditStreet?.setText(User.address.street)
        Profil_Editnumber?.setText(User.address.number)
    }

    override fun dataSaveOK(){
        val parentActivity= activity as MainActivity
        parentActivity.setUserOnDrawer()
        Toast.makeText(mainActivity, "Saved",Toast.LENGTH_SHORT).show()
    }
    override fun  dataSaveError(message :String){
        Toast.makeText(mainActivity, message,Toast.LENGTH_SHORT).show()
    }
}

