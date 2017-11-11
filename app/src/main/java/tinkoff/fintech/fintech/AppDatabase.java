package tinkoff.fintech.fintech;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import tinkoff.fintech.fintech.Dao.NodeDao;
import tinkoff.fintech.fintech.Dao.NodeWithClildrenDao;
import tinkoff.fintech.fintech.Entity.Node;
import tinkoff.fintech.fintech.Entity.NodeWithChildren;

@Database(entities = {Node.class, NodeWithChildren.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NodeDao nodeDao();

    public abstract NodeWithClildrenDao nodeWithClildrenDao();
}

