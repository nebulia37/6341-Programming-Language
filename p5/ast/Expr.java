package ast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public abstract class Expr extends ASTNode {
    protected Type type = null;
    protected AbstractVal value = null;

    public Expr(Location loc) {
        super(loc);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);

    public abstract void evaluate(Map<String, AbstractVal> valueTable, Scanner s);
}
