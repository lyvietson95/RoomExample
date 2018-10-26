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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
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
            // add Repo to database
            RepoDatabase.getsInstance(MainActivity.this).getRepoDao().insert(new Repo("11", "LVS Task", "Http:Son"));
            RepoDatabase.getsInstance(MainActivity.this).getRepoDao().insert(new Repo("22", "LVS Task 2", "Http:Son2"));
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
            // add Repo to database
            List<Repo> repos =  RepoDatabase.getsInstance(MainActivity.this).getRepoDao().getAllRepos();
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
