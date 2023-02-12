package ast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public abstract class CondExpr extends ASTNode {
    protected Type type;
    protected AbstractVal value;

    public CondExpr(Location loc) {
        super(loc);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);

    public abstract void evaluate(Map<String, AbstractVal> valueTable, Scanner s);
}
