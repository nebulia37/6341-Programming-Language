package ast;

import java.io.*;
import java.util.*;
import java.io.PrintStream;
import interpreter.Interpreter;

public class BinaryExpr extends Expr {
    public static final int PLUS = 1;
    public static final int MINUS = 2;
    public static final int TIMES = 3;
    public static final int DIV = 4;
    public final Expr expr1, expr2;
    public final int op;
    public BinaryExpr(Expr e1, int oper, Expr e2, Location loc) {
	super(loc);
	expr1 = e1; 
	expr2 = e2;
	op = oper;
    }
    public void print(PrintStream ps) {
	ps.print("(");
	expr1.print(ps);
	switch (op) {
	case PLUS: ps.print("+"); break;
	case MINUS: ps.print("-"); break;
	case TIMES: ps.print("*"); break;
	case DIV: ps.print("/"); break;
	}
	expr2.print(ps);
	ps.print(")");
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        expr1.check(tables);
        expr2.check(tables);

        if (expr1.type != expr2.type) {
            String opStr = null;
            switch (op) {
                case PLUS:
                    opStr = "+";
                    break;
                case MINUS:
                    opStr = "-";
                    break;
                case TIMES:
                    opStr = "*";
                    break;
                case DIV:
                    opStr = "/";
                    break;
            }

            Interpreter.fatalError("Type error in binary expr: " + expr1.type + " " + opStr + " " + expr2.type, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }

        type = expr1.type;
    }
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        expr1.evaluate(valueTable, s);
        expr2.evaluate(valueTable, s);

        boolean isLong = expr1.value instanceof Long && expr2.value instanceof Long;
        boolean isDouble = expr1.value instanceof Double && expr2.value instanceof Double;

        switch (op) {
            case PLUS:
                if (isLong) {
                    value = (Long) expr1.value + (Long) expr2.value;
                } else if (isDouble) {
                    value = (Double) expr1.value + (Double) expr2.value;
                }
                break;
            case MINUS:
                if (isLong) {
                    value = (Long) expr1.value - (Long) expr2.value;
                } else if (isDouble) {
                    value = (Double) expr1.value - (Double) expr2.value;
                }
                break;
            case TIMES:
                if (isLong) {
                    value = (Long) expr1.value * (Long) expr2.value;
                } else if (isDouble) {
                    value = (Double) expr1.value * (Double) expr2.value;
                }
                break;
            case DIV:
                if (isLong) {
                    if ((Long) expr2.value == 0L) {
                        Interpreter.fatalError("Divide by 0 is not permitted", Interpreter.EXIT_DIV_BY_ZERO_ERROR);
                    }
                    value = (Long) expr1.value / (Long) expr2.value;
                } else if (isDouble) {
                    if ((Double) expr2.value == 0.0) {
                        Interpreter.fatalError("Divide by 0 is not permitted", Interpreter.EXIT_DIV_BY_ZERO_ERROR);
                    }
                    value = (Double) expr1.value / (Double) expr2.value;
                }
                break;
        }
    }
}
