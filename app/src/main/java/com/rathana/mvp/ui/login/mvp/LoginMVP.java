package com.rathana.mvp.ui.login.mvp;

import com.rathana.mvp.model.User;

public interface LoginMVP {

    interface  View{
        void onSuccess(User user);
        void onError(String message);
        void onShowLoading();
        void onHideLoading();
    }

    interface Presenter{
        void login(User user);
        //void loadUserRole();
    }

    interface Interactor{
        void authenticate(User user, InteractorResponse response);

        interface  InteractorResponse {
            void onLoginFail(String msg);
            void onSuccess(User user);
        }
    }

}
