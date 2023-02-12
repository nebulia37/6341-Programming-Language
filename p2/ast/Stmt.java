package ast;

import java.util.ArrayList;
import java.util.Map;

public abstract class Stmt extends Unit {
    public Stmt(Location loc) {
        super(loc);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);
}
