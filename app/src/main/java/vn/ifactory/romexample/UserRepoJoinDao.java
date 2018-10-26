package vn.ifactory.romexample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by PC on 10/26/2018.
 */
@Dao
public interface UserRepoJoinDao {
    @Insert
    void insert(UserRepoJoin userRepoJoin);


    // get user by repoId
    @Query("SELECT * FROM users INNER JOIN user_repo_join ON " +
            "users.id = user_repo_join.userId WHERE " +
            "user_repo_join.repoId =:repoId")
    List<Users> getUserForRepository(String repoId);

    @Query("SELECT * FROM repo INNER JOIN user_repo_join ON " +
            "repo.id = user_repo_join.repoId WHERE " +
            "user_repo_join.userId =:userId")
    List<Repo> getRepositoriesByUser(int userId);

}
