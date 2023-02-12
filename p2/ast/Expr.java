package ast;

import java.util.ArrayList;
import java.util.Map;

public abstract class Expr extends ASTNode {
    protected Type type = null;

    public Expr(Location loc) {
        super(loc);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);
}
