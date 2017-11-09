package tinkoff.fintech.fintech.Entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;


public class NodeWithChildren {
    @Embedded
    public Node node;
    @Relation(parentColumn = "value",
            entityColumn = "value")
    public List<Node> children;
}
