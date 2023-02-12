package ast;

import interpreter.Interpreter;
import java.io.*;
import java.util.*;
import java.io.PrintStream;

public class FloatVarDecl extends VarDecl {
    public FloatVarDecl(String i, Location loc) {
	super(i,loc);
    }
    public void print(PrintStream ps) {
	ps.print("float " + ident);
    }
    
    public void check(ArrayList<Map<String, Type>> tables) {
        Map<String, Type> declTable = tables.get(tables.size() - 1);
        type = Type.FLOAT;

        if (declTable.containsKey(ident)) {
            Interpreter.fatalError("Double decl: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
    }

    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        assert true;
    }
}
