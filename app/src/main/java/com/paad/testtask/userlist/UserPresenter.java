package com.paad.testtask.userlist;

import android.app.Application;
import android.os.AsyncTask;

import com.paad.testtask.Repository;
import com.paad.testtask.model.User;
import com.paad.testtask.remote.ApiResult;
import com.paad.testtask.room.UserDao;
import com.paad.testtask.room.UserDatabaseManager;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class UserPresenter  implements UserContract.Presenter {

    private UserContract.View view;
    private Repository repository;
    private Application application;
    private UserDatabaseManager databaseManager;



    public UserPresenter(UserContract.View view, Application application) {
        this.view = view;
        this.application = application;
        repository = new Repository(application);
        databaseManager = UserDatabaseManager.getInstance();

    }

    @Override
    public void onLoadUser() {


        repository.getUsers(new ApiResult<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {


                view.showUserList(result);
            }

            @Override
            public void onFail() {
                view.showErrorMessage();
            }
        });


    }

    public void onSaveUsers(List<User> users) {

        new insertAsyncTask( databaseManager.init(application).getUserDao()).execute(users);

    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {

           List<User> users  = new GetUsersAsyncTask(databaseManager.init(application).getUserDao()).execute().get();


        return users;
    }

    private static class insertAsyncTask extends AsyncTask<List<User>, Void, Void> {
        private UserDao userDao;

        insertAsyncTask(UserDao _userDao) {
            userDao = _userDao;
        }

        @Override
        protected Void doInBackground(final List<User>... params) {
            userDao.insertAllUsers(params[0]); // This line throws the exception
            return null;
        }
    }


    private class GetUsersAsyncTask extends AsyncTask<Void, Void, List<User>>
    {
        private UserDao userDao;

        GetUsersAsyncTask(UserDao _userDao) {
            userDao = _userDao;
        }
        @Override
        protected List<User> doInBackground(Void... url) {
            return userDao.getAllUsers();
        }
    }


}
