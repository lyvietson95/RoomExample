package vn.ifactory.romexample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by PC on 10/26/2018.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<Users> getAllUsers();

    @Insert
    void insert(Users... users);

    @Update
    void update(Users... users);

    @Delete
    void delete(Users... users);


}
