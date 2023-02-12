package ast;
import java.io.PrintStream;
import java.util.*;

public abstract class CondExpr extends ASTNode {
    public CondExpr(Location loc) {
	super(loc);
    }
    
    public abstract boolean eval(Map<String, AS> st);
}
