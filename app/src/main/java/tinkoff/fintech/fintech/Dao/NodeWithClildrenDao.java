package tinkoff.fintech.fintech.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import tinkoff.fintech.fintech.Entity.NodeWithChildren;

@Dao
public interface NodeWithClildrenDao {
    @Query("SELECT * from nodewithchildren")
    List<NodeWithChildren> getNodesWithChildren();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addRelation(NodeWithChildren... withChildren);

    @Query("SELECT child from nodewithchildren WHERE node = (:id)")
    Integer[] getChildren(int id);

    @Query("SELECT node from nodewithchildren WHERE child = (:id)")
    Integer[] getParents(int id);
}
