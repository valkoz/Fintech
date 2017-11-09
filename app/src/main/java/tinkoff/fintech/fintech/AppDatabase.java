package tinkoff.fintech.fintech;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import tinkoff.fintech.fintech.Dao.NodeDao;
import tinkoff.fintech.fintech.Dao.NodeWithClildrenDao;
import tinkoff.fintech.fintech.Entity.Node;

@Database(entities = {Node.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NodeDao nodeDao();

    public abstract NodeWithClildrenDao nodeWithClildrenDao();
}

