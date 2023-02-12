package ast;
import java.io.PrintStream;
import java.util.*;

public abstract class VarDecl extends ASTNode {
    public final String ident;
    public VarDecl(String i, Location loc) {
	super(loc);
	ident = i;
    }
    public abstract void print(PrintStream ps);
    
    public abstract String check(Map<String, String> globalTable);
}
