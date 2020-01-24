package com.szakdolgozat.mygrades.ui.profil

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.base.BaseFragment
import com.szakdolgozat.mygrades.util.FormatDate
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_profile.*

import android.view.inputmethod.InputMethodManager


class ProfileFragment : BaseFragment<ProfilePresenter,ProfileView>(), ProfileView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindListeners()
        loadProfile()
    }

    override fun createPresenter(): ProfilePresenter {
        return ProfilePresenter(this)
    }

    override fun getFragmentActivity():Activity{
       return activity as Activity
    }

    fun bindListeners(){
        Profil_Avatar.setOnClickListener{
            onClickAvatar(it)
        }
        Profil_Avatar_Upload.setOnClickListener{
            onClickAvatar_Upload(it)
        }

        Profil_Name.setOnClickListener{
            onClickName(it)
        }
        Profil_Bday.setOnClickListener{
            onClickBirthDay(it)
        }

        Profil_Email.setOnClickListener{
            onClickEmail(it)
        }
        Profil_city.setOnClickListener{
            onClickcity(it)
        }
        Profil_Street.setOnClickListener{
            onClickStreet(it)
        }
        Profil_Zip.setOnClickListener{
            onClickZip(it)
        }
        Profil_number.setOnClickListener{
            onClicknumber(it)
        }
        floatingActionButton.setOnClickListener{
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
        presenter?.changeProfilePicture()
        setTextesDefault()
    }

    fun onClickName(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_EditName?.visibility=View.VISIBLE
        Profil_EditName?.setSelection(Profil_EditName?.length()?:0)
        showKeyBoard(Profil_EditName)
    }

    fun onClickEmail(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_EditEmail?.visibility=View.VISIBLE
        Profil_EditEmail?.setSelection(Profil_EditEmail?.length()?:0)
        showKeyBoard(Profil_EditEmail)
    }

    fun onClickcity(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_Editcity?.visibility=View.VISIBLE
        Profil_Editcity?.setSelection(Profil_Editcity?.length()?:0)
        showKeyBoard(Profil_Editcity)
    }

    fun onClickStreet(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_EditStreet?.visibility=View.VISIBLE
        Profil_EditStreet?.setSelection(Profil_EditStreet?.length()?:0)
        showKeyBoard(Profil_EditStreet)
    }

    fun onClickZip(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_EditZip?.visibility=View.VISIBLE
        Profil_EditZip?.setSelection(Profil_EditZip?.length()?:0)
        showKeyBoard(Profil_EditZip)
    }

    fun onClicknumber(v: View){
        setTextesDefault()
        v.visibility=View.INVISIBLE
        Profil_Editnumber?.visibility=View.VISIBLE
        Profil_Editnumber?.setSelection(Profil_Editnumber?.length()?:0)
        showKeyBoard(Profil_Editnumber)
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

    fun showKeyBoard(view: View){
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    fun onClickBirthDay(v :View){
        DatePickerDialog(
            containerActivity as Context,
            DatePickerDialog.OnDateSetListener(){view, year, month, dayOfMonth ->
                val real_month=month+1
                Profil_Bday?.text= "$year.$real_month.$dayOfMonth"
            },
            FormatDate.getYear(),
            (FormatDate.getMonth()-1),
            FormatDate.getDay()
        ).show()
    }

    fun saveProfile(v: View){
        setTextesDefault()
        setProfile()
        presenter?.saveProfile()
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
        if(User.Name!=null){
            Profil_Name?.text=User.Name
            Profil_EditName?.setText(User.Name)
        }

        if(User.birthday!=null){
            Profil_Bday?.text=User.birthday
        }

        if(User.address.city!=null){
            Profil_city?.text=User.address.city
            Profil_Editcity?.setText(User.address.city)
        }

        if(User.address.street!=null){
            Profil_Street?.text=User.address.street
            Profil_EditStreet?.setText(User.address.street)
        }

        if(User.address.zip!=null){
            Profil_Zip?.text=User.address.zip
            Profil_EditZip?.setText(User.address.zip)
        }

        if(User.address.number!=null){
            Profil_number?.text=User.address.number
            Profil_Editnumber?.setText(User.address.number)
        }

        Profil_Email?.text=User.email
        Profil_EditEmail?.setText(User.email)
    }

    override fun dataSaveOK(){
        val parentActivity= activity as? MainActivity
        parentActivity?.setUserOnDrawer()
        showMessage(getString(R.string.datas_saved))
    }
}

