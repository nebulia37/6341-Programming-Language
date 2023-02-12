package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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
        this(e1, oper, null, loc); // used for NOT
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
            case AND:
                ps.print("&&");
                break;
            case OR:
                ps.print("||");
                break;
        }
        expr2.print(ps);
        ps.print(")");
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        expr1.check(tables);

        if (op == NOT) {
            type = expr1.type;
            return;
        }

        expr2.check(tables);

        if (expr1.type != expr2.type) {
            Interpreter.fatalError("Type error in logical: " + expr1.type + " + " + expr2.type, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }

        type = Type.BOOL;
    }

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        expr1.evaluate(valueTable, s);

        if (this.op == NOT) {
            if (expr1.value == AbstractVal.True) {
                value = AbstractVal.False;
            } else if (expr1.value == AbstractVal.False) {
                value = AbstractVal.True;
            } else {
                value = AbstractVal.AnyBool;
            }
            return;
        }

        switch (op) {
            case AND:
                expr2.evaluate(valueTable, s);

                if (expr1.value == AbstractVal.True && expr2.value == AbstractVal.True) {
                    value = AbstractVal.True;
                } else if (expr1.value == AbstractVal.False || expr2.value == AbstractVal.False) {
                    value = AbstractVal.False;
                } else {
                    value = AbstractVal.AnyBool;
                }
                break;

            case OR:
                expr2.evaluate(valueTable, s);

                if (expr1.value == AbstractVal.True || expr2.value == AbstractVal.True) {
                    value = AbstractVal.True;
                } else if (expr1.value == AbstractVal.False && expr2.value == AbstractVal.False) {
                    value = AbstractVal.False;
                } else {
                    value = AbstractVal.AnyBool;
                }
                break;
        }
    }
}
