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
import java.util.Date;
import java.util.List;
import vn.ifactory.romexample.util.DateConverter;

@SuppressWarnings("unchecked")
public class RepoDao_Impl implements RepoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfRepo;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfRepo;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfRepo;

  public RepoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRepo = new EntityInsertionAdapter<Repo>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Repo`(`id`,`name`,`url`,`createAt`,`description`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Repo value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.id);
        }
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.url == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.url);
        }
        final long _tmp;
        _tmp = DateConverter.fromDate(value.createAt);
        stmt.bindLong(4, _tmp);
        if (value.description == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.description);
        }
      }
    };
    this.__deletionAdapterOfRepo = new EntityDeletionOrUpdateAdapter<Repo>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Repo` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Repo value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.id);
        }
      }
    };
    this.__updateAdapterOfRepo = new EntityDeletionOrUpdateAdapter<Repo>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Repo` SET `id` = ?,`name` = ?,`url` = ?,`createAt` = ?,`description` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Repo value) {
        if (value.id == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.id);
        }
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.url == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.url);
        }
        final long _tmp;
        _tmp = DateConverter.fromDate(value.createAt);
        stmt.bindLong(4, _tmp);
        if (value.description == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.description);
        }
        if (value.id == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.id);
        }
      }
    };
  }

  @Override
  public void insert(Repo... repos) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfRepo.insert(repos);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Repo... repos) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfRepo.handleMultiple(repos);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(Repo... repos) {
    __db.beginTransaction();
    try {
      __updateAdapterOfRepo.handleMultiple(repos);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Repo> getAllRepos() {
    final String _sql = "SELECT * FROM repo";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
      final int _cursorIndexOfCreateAt = _cursor.getColumnIndexOrThrow("createAt");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final List<Repo> _result = new ArrayList<Repo>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Repo _item;
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        final Date _tmpCreateAt;
        final long _tmp;
        _tmp = _cursor.getLong(_cursorIndexOfCreateAt);
        _tmpCreateAt = DateConverter.toDate(_tmp);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item = new Repo(_tmpId,_tmpName,_tmpUrl,_tmpCreateAt,_tmpDescription);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
