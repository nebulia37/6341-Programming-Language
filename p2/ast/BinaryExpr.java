package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

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
            case PLUS:
                ps.print("+");
                break;
            case MINUS:
                ps.print("-");
                break;
            case TIMES:
                ps.print("*");
                break;
            case DIV:
                ps.print("/");
                break;
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
}
