package ast;
import java.io.PrintStream;
import java.util.*;

public abstract class Unit extends ASTNode {
    public Map<String, String> decl = new HashMap<String, String>();
     
    public Unit(Location loc) {
	super(loc);
    }
    public abstract void print(PrintStream ps, String ident);
    
    public abstract void typeCheck(LinkedMap lm) throws Exception;
    
    public abstract void exec(Map<String, AS> st);
}
