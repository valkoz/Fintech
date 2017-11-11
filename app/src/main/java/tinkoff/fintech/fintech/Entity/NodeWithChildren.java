package tinkoff.fintech.fintech.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;


@Entity(primaryKeys = {"node", "child"},
        foreignKeys = {
                @ForeignKey(
                        entity = Node.class,
                        parentColumns = "value",
                        childColumns = "node"
                ),
                @ForeignKey(
                        entity = Node.class,
                        parentColumns = "value",
                        childColumns = "child"
                )
        }
)
public class NodeWithChildren {

    @NonNull
    public int node;

    @NonNull
    public int child;

    public NodeWithChildren(int node, int child) {
        this.node = node;
        this.child = child;
    }

    @Override
    public String toString() {
        return String.valueOf(node) + " : " + String.valueOf(child);
    }
}
