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
    TextView txtDisplayRepo;
    Button btnAddObj, btnGetObj;

    RepoDao repoDao;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        repoDao = RepoDatabase.getsInstance(MainActivity.this).getRepoDao();
        userDao = RepoDatabase.getsInstance(MainActivity.this).getUserDao();

        txtDisplayRepo = findViewById(R.id.txtDisplayRepo);
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
        new GetObjRepoTask().execute();
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
            repoDao.insert(new Repo("11", "LVS Task", "Http:Son", 69));
            repoDao.insert(new Repo("22", "LVS Task 2", "Http:Son2", 69));
            repoDao.insert(new Repo("33", "Repo sonAccount", "Http:sonAccount", 70));
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
            //List<Repo> repos =  RepoDatabase.getsInstance(MainActivity.this).getRepoDao().getAllRepos();

            // get repos by user id
            List<Repo> repos = repoDao.getReposByUserId(70);
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
}
