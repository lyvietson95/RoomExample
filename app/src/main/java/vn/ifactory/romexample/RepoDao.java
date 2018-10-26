package vn.ifactory.romexample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by PC on 10/25/2018.
 */

@Dao
public interface RepoDao {
    @Query("SELECT * FROM repo")
    List<Repo> getAllRepos();

    /* or query
        @Query("SELECT * FROM repo WHERE id=:id")
        Repo getRepo(int id);

        @Query("SELECT * FROM repo")
        Cursor getRepoCursor();


        // select using limit
        @Query("SELECT * FROM repo WHERE name=:name")
        List<Repo> getReposByName(String name);

        @Query("SELECT * FROM repo WHERE name=:name LIMIT :max")
        List<Repo> getReposByName(int max, String... name);
     */
    @Insert
    void insert(Repo... repos);

    // or insert
    /*
    @Insert
    void insert(Repo... repos);

    @Insert
    void insert(Repo repo);

    @Insert
    void insert(List<Repo> repoList);
    */

    @Update
    void update(Repo... repos);

    @Delete
    void delete(Repo... repos);
}
