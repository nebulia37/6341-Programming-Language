package ast;
import java.io.PrintStream;
import java.util.*;

public class Decl extends Unit {
    public final VarDecl varDecl;
    public final Expr expr;
    public Decl(VarDecl d, Location loc) {
	super(loc);
	varDecl = d;
	expr = null;
    }
    public Decl(VarDecl d, Expr e, Location loc) {
	super(loc);
	varDecl = d;
	expr = e;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent);
	varDecl.print(ps); 
	if (expr != null) {
	    ps.print(" = ");
	    expr.print(ps);
	}
	ps.print(";");
    }
    public void print(PrintStream ps) {
	print(ps,"");
    }
    
    public void typeCheck(LinkedMap lm) throws Exception{
    	// Check repeated declration
		if(lm.tbl.containsKey(varDecl.ident))
			throw new Exception("\nident." + varDecl.ident + " has been declared");
		
		// VarDecl = Expr;
		if(expr != null){
	    	expr.typeCheck(lm);
	    	lm.tbl.putAll(varDecl.decl);
	    	if(!expr.type.equals(lm.tbl.get(varDecl.ident)))
				throw new Exception("\nident." + varDecl.ident + " type is not equal to expr.type");  	
    	} else {
    		lm.tbl.putAll(varDecl.decl);
    	}
    }
    
    public void exec(Map<String, AS> st) {
    	if(expr != null)
    		st.put(varDecl.ident, expr.eval(st));
    }
}
