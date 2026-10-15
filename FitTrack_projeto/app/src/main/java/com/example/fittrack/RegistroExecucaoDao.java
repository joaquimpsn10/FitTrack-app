package com.example.fittrack;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RegistroExecucaoDao {

    @Query("SELECT * FROM registros_execucao WHERE treinoId = :treinoId")
    List<RegistroExecucaoEntity> getForTreino(int treinoId);

    @Insert
    void insertAll(List<RegistroExecucaoEntity> registros);

    @Update
    void update(RegistroExecucaoEntity registro);

    @Query("DELETE FROM registros_execucao WHERE treinoId = :treinoId")
    void deleteForTreino(int treinoId);
}
