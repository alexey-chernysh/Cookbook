package ru.chernysh.cookbook.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MaterialDao {

    @Query("SELECT * FROM material")
    List<Material> getAll();

    @Query("SELECT * FROM material WHERE id = :id")
    Material getById(long id);

    @Query("SELECT * FROM material WHERE id IN (:materialIds)")
    List<Material> loadAllByIds(int[] materialIds);
}
