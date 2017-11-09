package tinkoff.fintech.fintech.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import tinkoff.fintech.fintech.Entity.NodeWithChildren;

@Dao
public interface NodeWithClildrenDao {
    @Query("SELECT * from node")
    public List<NodeWithChildren> getNodesWithChildrens();
}
