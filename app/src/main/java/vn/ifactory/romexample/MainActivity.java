package vn.ifactory.romexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    TextView txtDisplayRepo, txtDisplayUser;
    Button btnAddObj, btnGetObj;

    RepoDao repoDao;
    UserDao userDao;
    UserRepoJoinDao userRepoJoinDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        repoDao = RepoDatabase.getsInstance(MainActivity.this).getRepoDao();
        userDao = RepoDatabase.getsInstance(MainActivity.this).getUserDao();
        userRepoJoinDao = RepoDatabase.getsInstance(MainActivity.this).getUserRepoJoinDao();

        txtDisplayRepo = findViewById(R.id.txtDisplayRepo);
        txtDisplayUser = findViewById(R.id.txtDisplayUser);
        btnAddObj = findViewById(R.id.btnAddObjRepo);
        btnGetObj = findViewById(R.id.btnGetObj);

        btnAddObj.setOnClickListener(this);
        btnGetObj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddObjRepo:
                addObjRepo();
                break;
            case R.id.btnGetObj:
                getObjRepo();
                break;
                default:break;
        }
    }

    private void getObjRepo() {
        //new GetObjRepoTask().execute();
        new GetRepositoriesByUserTask().execute();

        new GetUserByRepoTask().execute();
    }

    private void addObjRepo() {
        new AddObjRepoTask().execute();
    }

    class AddObjRepoTask extends AsyncTask<Void, Void , Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new Users(69, "sonlv", "sonlv-Http"));
            userDao.insert(new Users(70, "sonAccount", "sonAccount-Http"));

            // add Repo to database
            repoDao.insert(new Repo("11", "Repo 1", "Http:Son"));
            repoDao.insert(new Repo("22", "Repo 2", "Http:Son2"));
            repoDao.insert(new Repo("33", "Repo 3", "Http:sonAccount"));

            // add userRepo for table
            userRepoJoinDao.insert(new UserRepoJoin(69, "33"));
            userRepoJoinDao.insert(new UserRepoJoin(69, "22"));
            userRepoJoinDao.insert(new UserRepoJoin(70, "22"));


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    class GetObjRepoTask extends AsyncTask<Void, Void , List<Repo>>{

        @Override
        protected List<Repo> doInBackground(Void... voids) {
            // get all Repo
            List<Repo> repos =  RepoDatabase.getsInstance(MainActivity.this).getRepoDao().getAllRepos();


            // get repos by user id
            return repos;
        }

        @Override
        protected void onPostExecute(List<Repo> repos) {
            super.onPostExecute(repos);
            Log.d(TAG, "Get Repo Successful");
            String dataRepo = "";
            for (Repo repo : repos) {
                dataRepo += repo.name;
            }

            txtDisplayRepo.setText(dataRepo);
        }
    }

    class GetRepositoriesByUserTask extends AsyncTask<Void, Void , List<Repo>>{

        @Override
        protected List<Repo> doInBackground(Void... voids) {
            // get all Repo by user

            List<Repo> userRepoJoins = userRepoJoinDao.getRepositoriesByUser(69);
            // get repos by user id
            return userRepoJoins;
        }

        @Override
        protected void onPostExecute(List<Repo> repos) {
            super.onPostExecute(repos);
            Log.d(TAG, "Get Repo Successful");

            String repoByUser = "";

            for (Repo repo : repos){
                repoByUser += repo.name + "\n";
            }
            Log.d(TAG, "Repo by User: " + repoByUser);
            txtDisplayRepo.setText("Repo by User: \n" + repoByUser);
        }
    }


    class GetUserByRepoTask extends AsyncTask<Void, Void , List<Users>>{

        @Override
        protected List<Users> doInBackground(Void... voids) {
            // get all Repo by user

            List<Users> users = userRepoJoinDao.getUserForRepository("22");
            // get repos by user id
            return users;
        }

        @Override
        protected void onPostExecute(List<Users> users) {
            super.onPostExecute(users);
            Log.d(TAG, "Get Repo Successful");

            String userByRepo = "";

            for (Users user : users){
                userByRepo += user.login + "\n";
            }
            Log.d(TAG, "User by Repo: " + userByRepo);
            txtDisplayUser.setText("User By RepoId: " + "\n" + userByRepo);
        }
    }
}
