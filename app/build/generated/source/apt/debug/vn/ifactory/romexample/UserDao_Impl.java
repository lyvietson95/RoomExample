package vn.ifactory.romexample;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUsers;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfUsers;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfUsers;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsers = new EntityInsertionAdapter<Users>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Users`(`id`,`login`,`avatar`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Users value) {
        stmt.bindLong(1, value.id);
        if (value.login == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.login);
        }
        if (value.avatar == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.avatar);
        }
      }
    };
    this.__deletionAdapterOfUsers = new EntityDeletionOrUpdateAdapter<Users>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Users` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Users value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfUsers = new EntityDeletionOrUpdateAdapter<Users>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Users` SET `id` = ?,`login` = ?,`avatar` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Users value) {
        stmt.bindLong(1, value.id);
        if (value.login == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.login);
        }
        if (value.avatar == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.avatar);
        }
        stmt.bindLong(4, value.id);
      }
    };
  }

  @Override
  public void insert(Users... users) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsers.insert(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Users... users) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfUsers.handleMultiple(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(Users... users) {
    __db.beginTransaction();
    try {
      __updateAdapterOfUsers.handleMultiple(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Users> getAllUsers() {
    final String _sql = "SELECT * FROM users";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfLogin = _cursor.getColumnIndexOrThrow("login");
      final int _cursorIndexOfAvatar = _cursor.getColumnIndexOrThrow("avatar");
      final List<Users> _result = new ArrayList<Users>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Users _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpLogin;
        _tmpLogin = _cursor.getString(_cursorIndexOfLogin);
        final String _tmpAvatar;
        _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
        _item = new Users(_tmpId,_tmpLogin,_tmpAvatar);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
