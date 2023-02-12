package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;

public abstract class Stmt extends Unit {
    public Stmt(Location loc) {
	super(loc);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);
    public abstract void evaluate(Map<String, Number> valueTable, Scanner s);
}
