package ast;

import interpreter.Interpreter;
import java.io.*;
import java.util.*;
import java.io.PrintStream;

public abstract class CondExpr extends ASTNode {
    protected Type type;
    public CondExpr(Location loc) {
	super(loc);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);
    public abstract void evaluate(Map<String, Number> valueTable, Scanner s);
}
