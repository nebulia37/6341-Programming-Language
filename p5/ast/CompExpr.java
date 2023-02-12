package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class CompExpr extends CondExpr {
    public static final int GE = 1;
    public static final int GT = 2;
    public static final int LE = 3;
    public static final int LT = 4;
    public static final int EQ = 5;
    public static final int NE = 6;
    public final Expr expr1, expr2;
    public final int op;

    public CompExpr(Expr e1, int oper, Expr e2, Location loc) {
        super(loc);
        expr1 = e1;
        expr2 = e2;
        op = oper;
    }

    public void print(PrintStream ps) {
        ps.print("(");
        expr1.print(ps);
        switch (op) {
            case GE:
                ps.print(">=");
                break;
            case GT:
                ps.print(">");
                break;
            case LE:
                ps.print("<=");
                break;
            case LT:
                ps.print("<");
                break;
            case EQ:
                ps.print("==");
                break;
            case NE:
                ps.print("!=");
                break;
        }
        expr2.print(ps);
        ps.print(")");
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        expr1.check(tables);
        expr2.check(tables);

        if (expr1.type != expr2.type) {
            String opStr = null;
            switch (op) {
                case GE:
                    opStr = ">=";
                    break;
                case GT:
                    opStr = ">";
                    break;
                case LE:
                    opStr = "<=";
                    break;
                case LT:
                    opStr = "<";
                    break;
                case EQ:
                    opStr = "==";
                    break;
                case NE:
                    opStr = "!=";
                    break;
            }
            Interpreter.fatalError("Type error in compare: " + expr1.type + " " + opStr + " " + expr2.type, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }

        type = Type.BOOL;
    }

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        expr1.evaluate(valueTable, s);
        expr2.evaluate(valueTable, s);

        AbstractVal expr1Val = expr1.value;
        AbstractVal expr2Val = expr2.value;
        AbstractVal equal = AbstractVal.False;

        if (expr1Val == expr2Val) {
            equal = AbstractVal.True;
        }

        switch (op) {
            case GE:
                if (equal == AbstractVal.True) {
                    value = AbstractVal.True;
                    return;
                }
            case GT:
                if (expr1Val == AbstractVal.Pos) {
                    if (expr2Val == AbstractVal.Zero || expr2Val == AbstractVal.Neg) {
                        value = AbstractVal.True;
                    }
                } else if (expr1Val == AbstractVal.Zero) {
                    if (expr2Val == AbstractVal.Neg) {
                        value = AbstractVal.True;
                    }
                }
                break;
            case LE:
                if (equal == AbstractVal.True) {
                    value = AbstractVal.True;
                    return;
                }
            case LT:
                if (expr1Val == AbstractVal.Neg) {
                    if (expr2Val == AbstractVal.Zero || expr2Val == AbstractVal.Pos) {
                        value = AbstractVal.True;
                    }
                } else if (expr1Val == AbstractVal.Zero) {
                    if (expr2Val == AbstractVal.Pos) {
                        value = AbstractVal.True;
                    }
                }
                break;
            case EQ:
                value = equal;
                break;
            case NE:
                if (equal == AbstractVal.False) {
                    value = AbstractVal.True;
                }
                break;
        }
    }
}
