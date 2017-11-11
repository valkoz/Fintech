package tinkoff.fintech.fintech.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import tinkoff.fintech.fintech.Entity.Node;

@Dao
public interface NodeDao {
    @Query("SELECT * FROM Node")
    List<Node> getAll();

    @Query("SELECT * FROM Node WHERE value = (:id)")
    Node getByKey(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Node... nodes);

    @Query("DELETE FROM Node")
    void deleteAll();

}
