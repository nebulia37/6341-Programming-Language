package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;
import interpreter.Interpreter;

public class IntVarDecl extends VarDecl {
    public IntVarDecl(String i, Location loc) {
	super(i,loc);
    }
    public void print(PrintStream ps) {
	ps.print("int " + ident);
    }
    
     public void check(ArrayList<Map<String, Type>> tables) {
        Map<String, Type> declTable = tables.get(tables.size() - 1);
        type = Type.INT;

        if (declTable.containsKey(ident)) {
            Interpreter.fatalError("Double decl: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
    }
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        assert true;
    }
}
