package com.example.calculator.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> loadAllUser();

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

//    @Query("SELECT * FROM user where userId= :idAdded")
//    User selectById(String idAdded);

}
