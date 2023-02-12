package ast;
import java.io.PrintStream;
import java.util.*;

public abstract class VarDecl extends ASTNode {
    public final String ident;
    public Map<String, String> decl = new HashMap<>();
    
    public VarDecl(String i, Location loc) {
	super(loc);
	ident = i;
    }
    public abstract void print(PrintStream ps);
    
    public abstract void typeCheck(LinkedMap lm);
}
