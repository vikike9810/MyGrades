package com.szakdolgozat.mygrades.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.base.BaseFragment
import com.szakdolgozat.mygrades.ui.login.LoginView
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment<SignUpPresenter,SignUpView>(), SignUpView {
    lateinit var loginActivity: LoginView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivity=activity as LoginView
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_sign_up, container, false)
        view.findViewById<TextView>(R.id.SignUp_OK).setOnClickListener{
            onClickOK(it)
        }
        view.findViewById<TextView>(R.id.SignUp_Cancel).setOnClickListener{
            onClickCancel(it)
        }
        return view
    }

    override fun createPresenter(): SignUpPresenter {
        return SignUpPresenter(this)
    }

    override fun getFragmentActivity(): FragmentActivity?{
        return activity
    }


    fun onClickOK(v: View) {
        if(checkFieldIsOk(SignUp_EditName) && checkFieldIsOk(SignUp_EditEmail) && checkFieldIsOk(SignUp_EditPassw)){
            val selectedRadio= SignUp_Group.findViewById<RadioButton>(SignUp_Group.checkedRadioButtonId)
            presenter?.Registration(SignUp_EditEmail.text.toString(),
                SignUp_EditPassw.text.toString(),
                SignUp_EditName.text.toString(),
                selectedRadio.text.toString())
        }
    }


    fun onClickCancel(v: View) {
        loginActivity.returnFromSignup()
    }

    override fun signUpOk(){
        showMessage("Registration was successful")
        loginActivity.returnFromSignup()
    }

    override fun signUpError(message: String) {
        showMessage(message)
    }

    fun checkFieldIsOk(field: EditText):Boolean {
        if(field.text.isEmpty()){
            field.setError("This field is required!")
            return false
        }
        if(field.tag=="passw" && field.text.length<6){
            field.setError("Password must be minimum 6 character!")
            return false
        }
        return true
    }


}
