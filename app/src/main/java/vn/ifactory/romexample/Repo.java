package vn.ifactory.romexample;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by PC on 10/25/2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = Users.class,
                                    parentColumns = "id",
                                    childColumns = "userId",
                                    onDelete = CASCADE))
public class Repo {
    @PrimaryKey
    @NonNull
    public final String id;
    public final String name;
    public final String url;
    public final int userId;

    public Repo(String id, String name, String url, int userId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.userId = userId;
    }
}
