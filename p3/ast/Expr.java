package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;

public abstract class Expr extends ASTNode {
    protected Type type;
    protected Number value = null;
  
    public Expr(Location loc) {
	super(loc);
    }
  
    public abstract void check(ArrayList<Map<String, Type>> tables);
    public abstract void evaluate(Map<String, Number> valueTable, Scanner s);
}
