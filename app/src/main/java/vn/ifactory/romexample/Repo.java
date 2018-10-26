package vn.ifactory.romexample;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by PC on 10/25/2018.
 */

@Entity
public class Repo {
    @PrimaryKey
    @NonNull
    public final String id;
    public final String name;
    public final String url;
    public Date createAt;
    public final String description;

    public Repo(String id, String name, String url, Date createAt, String description) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.createAt = createAt;
        this.description = description;
    }
}
