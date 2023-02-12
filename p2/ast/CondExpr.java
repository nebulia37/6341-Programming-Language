package ast;

import java.util.ArrayList;
import java.util.Map;

public abstract class CondExpr extends ASTNode {
    protected Type type;

    public CondExpr(Location loc) {
        super(loc);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);
}
