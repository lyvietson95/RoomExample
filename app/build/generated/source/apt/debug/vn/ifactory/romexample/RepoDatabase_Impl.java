package vn.ifactory.romexample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class RepoDatabase_Impl extends RepoDatabase {
  private volatile RepoDao _repoDao;

  private volatile UserDao _userDao;

  private volatile UserRepoJoinDao _userRepoJoinDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Repo` (`id` TEXT NOT NULL, `name` TEXT, `url` TEXT, `createAt` INTEGER, `description` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Users` (`id` INTEGER NOT NULL, `login` TEXT, `avatar` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user_repo_join` (`userId` INTEGER NOT NULL, `repoId` TEXT NOT NULL, PRIMARY KEY(`userId`, `repoId`), FOREIGN KEY(`userId`) REFERENCES `Users`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`repoId`) REFERENCES `Repo`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"e96dbaad5267bb648f5efd19d91520e3\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Repo`");
        _db.execSQL("DROP TABLE IF EXISTS `Users`");
        _db.execSQL("DROP TABLE IF EXISTS `user_repo_join`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsRepo = new HashMap<String, TableInfo.Column>(5);
        _columnsRepo.put("id", new TableInfo.Column("id", "TEXT", true, 1));
        _columnsRepo.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsRepo.put("url", new TableInfo.Column("url", "TEXT", false, 0));
        _columnsRepo.put("createAt", new TableInfo.Column("createAt", "INTEGER", false, 0));
        _columnsRepo.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRepo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRepo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRepo = new TableInfo("Repo", _columnsRepo, _foreignKeysRepo, _indicesRepo);
        final TableInfo _existingRepo = TableInfo.read(_db, "Repo");
        if (! _infoRepo.equals(_existingRepo)) {
          throw new IllegalStateException("Migration didn't properly handle Repo(vn.ifactory.romexample.Repo).\n"
                  + " Expected:\n" + _infoRepo + "\n"
                  + " Found:\n" + _existingRepo);
        }
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(3);
        _columnsUsers.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsUsers.put("login", new TableInfo.Column("login", "TEXT", false, 0));
        _columnsUsers.put("avatar", new TableInfo.Column("avatar", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("Users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(_db, "Users");
        if (! _infoUsers.equals(_existingUsers)) {
          throw new IllegalStateException("Migration didn't properly handle Users(vn.ifactory.romexample.Users).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsUserRepoJoin = new HashMap<String, TableInfo.Column>(2);
        _columnsUserRepoJoin.put("userId", new TableInfo.Column("userId", "INTEGER", true, 1));
        _columnsUserRepoJoin.put("repoId", new TableInfo.Column("repoId", "TEXT", true, 2));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserRepoJoin = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysUserRepoJoin.add(new TableInfo.ForeignKey("Users", "NO ACTION", "NO ACTION",Arrays.asList("userId"), Arrays.asList("id")));
        _foreignKeysUserRepoJoin.add(new TableInfo.ForeignKey("Repo", "NO ACTION", "NO ACTION",Arrays.asList("repoId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesUserRepoJoin = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserRepoJoin = new TableInfo("user_repo_join", _columnsUserRepoJoin, _foreignKeysUserRepoJoin, _indicesUserRepoJoin);
        final TableInfo _existingUserRepoJoin = TableInfo.read(_db, "user_repo_join");
        if (! _infoUserRepoJoin.equals(_existingUserRepoJoin)) {
          throw new IllegalStateException("Migration didn't properly handle user_repo_join(vn.ifactory.romexample.UserRepoJoin).\n"
                  + " Expected:\n" + _infoUserRepoJoin + "\n"
                  + " Found:\n" + _existingUserRepoJoin);
        }
      }
    }, "e96dbaad5267bb648f5efd19d91520e3", "096a71a3c87d94604855d9be1e0d35f0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Repo","Users","user_repo_join");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `user_repo_join`");
      _db.execSQL("DELETE FROM `Repo`");
      _db.execSQL("DELETE FROM `Users`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public RepoDao getRepoDao() {
    if (_repoDao != null) {
      return _repoDao;
    } else {
      synchronized(this) {
        if(_repoDao == null) {
          _repoDao = new RepoDao_Impl(this);
        }
        return _repoDao;
      }
    }
  }

  @Override
  public UserDao getUserDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public UserRepoJoinDao getUserRepoJoinDao() {
    if (_userRepoJoinDao != null) {
      return _userRepoJoinDao;
    } else {
      synchronized(this) {
        if(_userRepoJoinDao == null) {
          _userRepoJoinDao = new UserRepoJoinDao_Impl(this);
        }
        return _userRepoJoinDao;
      }
    }
  }
}
