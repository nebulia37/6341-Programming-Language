package ast;
import java.io.PrintStream;
import java.util.*;
import interpreter.Interpreter;

public class IfStmt extends Stmt {
    public final CondExpr expr; 
    public final Stmt thenstmt, elsestmt;
    public IfStmt(CondExpr e, Stmt s, Location loc) {
	super(loc);
	expr = e;
	thenstmt = s;
	elsestmt = null;
    }
    public IfStmt(CondExpr e, Stmt s1, Stmt s2, Location loc) {
	super(loc);
	expr = e;
	thenstmt = s1;
	elsestmt = s2;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent + "if (");
	expr.print(ps);
	ps.print(")\n");
	thenstmt.print(ps, indent+"  ");
	if (elsestmt != null) {
	    ps.print("\n" + indent + "else\n");	    
	    elsestmt.print(ps, indent + "  ");
	}
    }
    public void print(PrintStream ps) {     
	print(ps,"");
    }
    
    public void typeCheck(LinkedMap lm) throws Exception {
    	expr.typeCheck(lm);
    	thenstmt.typeCheck(new LinkedMap(lm));
    	if (elsestmt != null){
    		elsestmt.typeCheck(new LinkedMap(lm));
    	}
    }
    
    public void exec(Map<String, AS> st) {
    	Map<String, AS> st2 = new HashMap<>();
    	st2.putAll(st);
    	// if
    	expr.eval(st);
    	thenstmt.exec(st);
    	// else
    	elsestmt.exec(st2);
    	// Merge
    	for (String var: st2.keySet()) {
    		AS as2 = st2.get(var);
    		int v2 = as2.v();
    		if (st.containsKey(var)) {
    			AS as1 = st.get(var);
    			int v1 = as1.v();
    			if (Interpreter.lm.get(var).equals("int")) {
    				st.put(var, ArthTable.MergeTableInt[v1][v2]);
    			} else {
    				if (as1 == AS.AnyFloat) v1--;
    				if (as2 == AS.AnyFloat) v2--;
    				st.put(var, ArthTable.MergeTableFloat[v1][v2]);
    			}
    		} else {
    			st.put(var, as2);
    		}
    	}
    }
}
