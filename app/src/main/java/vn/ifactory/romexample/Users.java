package vn.ifactory.romexample;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by PC on 10/26/2018.
 */

@Entity
public class Users {
    @PrimaryKey
    @NonNull
    public final int id;
    public final String login;
    public final String avatar;

    public Users(int id, String login, String avatar) {
        this.id = id;
        this.login = login;
        this.avatar = avatar;
    }
}
