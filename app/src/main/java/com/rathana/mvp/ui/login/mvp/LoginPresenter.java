package com.rathana.mvp.ui.login.mvp;

import com.rathana.mvp.model.User;

public class LoginPresenter implements LoginMVP.Presenter {

    LoginMVP.Interactor interactor;
    LoginMVP.View view;

    public LoginPresenter(LoginMVP.View view) {
        this.view = view;
        interactor=new LoginInteractor();
    }

    @Override
    public void login(User user) {

        //delegate login event to ineractor
        view.onShowLoading();
        interactor.authenticate(user, new LoginMVP.Interactor.InteractorResponse() {
            @Override
            public void onLoginFail(String msg) {
                view.onHideLoading();
                view.onError(msg);
            }

            @Override
            public void onSuccess(User user) {
                view.onHideLoading();
                view.onSuccess(user);
            }
        });

    }
}
