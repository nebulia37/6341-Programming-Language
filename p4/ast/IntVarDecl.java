package ast;
import java.io.PrintStream;

public class IntVarDecl extends VarDecl {
    public IntVarDecl(String i, Location loc) {
	super(i,loc);
	super.decl.put(i, "int");
    }
    public void print(PrintStream ps) {
	ps.print("int " + ident);
    }
    
    public void typeCheck(LinkedMap lm) {}
}
