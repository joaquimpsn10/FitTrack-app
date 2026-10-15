package com.example.fittrack;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TreinoDao {

    @Query("SELECT * FROM treinos")
    List<TreinoEntity> getAll();

    @Insert
    long insert(TreinoEntity treino);

    @Update
    void update(TreinoEntity treino);

    @Query("DELETE FROM treinos WHERE id = :id")
    void deleteById(int id);

    @Query("DELETE FROM treinos WHERE nome = :nome")
    void deleteByNome(String nome);
}
