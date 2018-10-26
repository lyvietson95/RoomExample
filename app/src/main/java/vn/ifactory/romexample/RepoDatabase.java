package vn.ifactory.romexample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by PC on 10/25/2018.
 */

@Database(entities = {Repo.class, Users.class, UserRepoJoin.class}, version = 1)
public abstract class RepoDatabase extends RoomDatabase {
    private static final String DB_NAME = "repoDatabase.db";
    private static volatile RepoDatabase sInstance;

    static synchronized RepoDatabase getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = create(context);
        }
        return sInstance;
    }

    private static RepoDatabase create(Context context) {
        return Room.databaseBuilder(
                context,
                RepoDatabase.class,
                DB_NAME).build();
    }

    /*
    This is very important because database class should have these things at the minimum:
    - @Database annotation
    - class to be abstract and extend from RoomDatabase class
    - class have an abstract method with no parameters and returns the class that is annotated with @Dao (in our case: getRepoDao())
     */
    public abstract RepoDao getRepoDao();

    public abstract UserDao getUserDao();

    public abstract UserRepoJoinDao getUserRepoJoinDao();
}
