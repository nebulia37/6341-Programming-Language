package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;
import interpreter.Interpreter;

public class LogicalExpr extends CondExpr {
    public static final int AND = 1;
    public static final int OR = 2;
    public static final int NOT = 3;
    public final CondExpr expr1, expr2;
    public final int op;
    public LogicalExpr(CondExpr e1, int oper, CondExpr e2, Location loc) {
	super(loc);
	expr1 = e1; 
	expr2 = e2;
	op = oper;
    }
    public LogicalExpr(CondExpr e1, int oper, Location loc) {
	this(e1,oper,null,loc); // used for NOT
    }
    public void print(PrintStream ps) {
	if (op == NOT) {
	    ps.print("!(");
	    expr1.print(ps);
	    ps.print(")");
	    return;
	}
	ps.print("(");
	expr1.print(ps);
	switch (op) { 
	case AND: ps.print("&&"); break;
	case OR: ps.print("||"); break;
	}
	expr2.print(ps);
	ps.print(")");
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        expr1.check(tables);
        expr2.check(tables);

        if (expr1.type != expr2.type) {
            Interpreter.fatalError("Type error in logical: " + expr1.type + " + " + expr2.type, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }

        type = expr1.type;
    }

    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        expr1.evaluate(valueTable, s);

        if (this.op == NOT) {
            value = !expr1.value;
            return;
        }

        switch (op) {
            case AND:
                if (expr1.value) {
                    expr2.evaluate(valueTable, s);
                } else {
                    value = false;
                    return;
                }

                value = expr1.value && expr2.value;
                break;
            case OR:
                if (expr1.value) {
                    value = true;
                    return;
                }

                value = expr2.value;
                break;
        }
    }
}
