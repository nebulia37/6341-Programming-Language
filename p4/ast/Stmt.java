package ast;
import java.io.PrintStream;
import java.util.*;

public abstract class Stmt extends Unit {
	public Map<String, String> decl = new HashMap<>();
	
    public Stmt(Location loc) {
	super(loc);
    }
    
    public abstract void typeCheck(LinkedMap lm) throws Exception;
    
}
