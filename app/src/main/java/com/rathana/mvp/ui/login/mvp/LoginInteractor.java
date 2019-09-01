package com.rathana.mvp.ui.login.mvp;

import android.os.Handler;

import com.rathana.mvp.model.User;

public class LoginInteractor implements LoginMVP.Interactor {


    //service
    //getway

    final User userDefault =new User("admin","Admin");
    @Override
    public void authenticate(User user, InteractorResponse response) {

        new Handler().postDelayed(()->{
            //authenticate
            if(userDefault.getName().equals(user.getName()) &&
            userDefault.getPassword().equals(user.getPassword())){
                response.onSuccess(userDefault);
            }else {
                response.onLoginFail("login fail! You may enter wrong name or password");
            }

        },1000);
    }

}
