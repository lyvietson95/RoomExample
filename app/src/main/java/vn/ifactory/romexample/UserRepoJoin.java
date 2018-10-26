package vn.ifactory.romexample;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

/**
 * Created by PC on 10/26/2018.
 */

@Entity(tableName = "user_repo_join",
                    primaryKeys = {"userId", "repoId"},
                    foreignKeys = {
                            @ForeignKey(entity = Users.class,
                                        parentColumns = "id",
                                        childColumns = "userId"),
                            @ForeignKey(entity = Repo.class,
                                        parentColumns = "id",
                                        childColumns = "repoId")
                    })
public class UserRepoJoin {
    @NonNull
    public final int userId;
    @NonNull
    public final String repoId;

    public UserRepoJoin(int userId, String repoId) {
        this.userId = userId;
        this.repoId = repoId;
    }
}
