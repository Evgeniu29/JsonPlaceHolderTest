package com.paad.testtask.userlist;



import com.paad.testtask.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserContract {

    interface View {


        void showUserList(List<User> users);
        void showErrorMessage();
    }

    interface Presenter {

        void onLoadUser();

        void onSaveUsers(List<User> users);


       List<User> getAllUsers() throws ExecutionException, InterruptedException;


    }
}
