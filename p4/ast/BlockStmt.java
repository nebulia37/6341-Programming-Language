package ast;
import java.io.PrintStream;
import java.util.*;

public class BlockStmt extends Stmt {
    public final UnitList block;
    public BlockStmt(UnitList b, Location loc) {
	super(loc);
	block = b;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent + "{\n");
	block.print(ps, indent + "  ");
	ps.print(indent + "}");
    }
    public void print(PrintStream ps) {     
	print(ps,"");
    }
    
    public void typeCheck(LinkedMap lm) throws Exception {
    	block.typeCheck(new LinkedMap(lm));
    }
    
    public void exec(Map<String, AS> st) {
    	block.exec(st);
    }
}
