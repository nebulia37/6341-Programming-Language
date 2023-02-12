package ast;
import java.io.PrintStream;
import java.util.*;

public abstract class Expr extends ASTNode {
	public String type;
	
    public Expr(Location loc) {
	super(loc);
    }
    
    public abstract void typeCheck(LinkedMap tbl) throws Exception;
    
    public abstract AS eval(Map<String, AS> st);
    
}
