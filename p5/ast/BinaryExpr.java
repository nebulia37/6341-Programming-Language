package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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

    @Override
    public void evaluate(Map<String, AbstractVal> valueTable, Scanner s) {
        expr1.evaluate(valueTable, s);
        expr2.evaluate(valueTable, s);

        Type exprType = expr1.type;
        AbstractVal expr1Val = expr1.value;
        AbstractVal expr2Val = expr2.value;

        boolean posAndNeg = (expr1Val == AbstractVal.Pos && expr2Val == AbstractVal.Neg) ||
                (expr1Val == AbstractVal.Neg && expr2Val == AbstractVal.Pos);

        AbstractVal anyType = AbstractVal.AnyInt;
        if (type == Type.FLOAT) {
            anyType = AbstractVal.AnyFloat;
        }

        boolean hasAny = false;
        if (expr1Val == AbstractVal.AnyInt || expr2Val == AbstractVal.AnyInt) {
            hasAny = true;
        } else if (expr1Val == AbstractVal.AnyFloat || expr2Val == AbstractVal.AnyFloat) {
            hasAny = true;
        }

        switch (op) {
            case PLUS:
                if (expr1Val == expr2Val) {
                    value = expr1Val;
                } else if (hasAny) {
                    value = anyType;
                } else if (posAndNeg) {
                    value = anyType;
                } else if (expr1Val == AbstractVal.Zero || expr2Val == AbstractVal.Zero) {
                    if (expr1Val != AbstractVal.Zero) {
                        value = expr1Val;
                    } else {
                        value = expr2Val;
                    }
                }
                break;
            case MINUS:
                if (hasAny) {
                    value = anyType;
                } else if (expr1Val == AbstractVal.Pos) {
                    switch (expr2Val) {
                        case Pos:
                            value = anyType;
                            break;
                        default:
                            value = AbstractVal.Pos;
                            break;
                    }
                } else if (expr1Val == AbstractVal.Zero) {
                    switch (expr2Val) {
                        case Neg:
                            value = AbstractVal.Pos;
                            break;
                        case Zero:
                            value = AbstractVal.Zero;
                            break;
                        case Pos:
                            value = AbstractVal.Neg;
                            break;
                    }
                } else if (expr1Val == AbstractVal.Neg) {
                    switch (expr2Val) {
                        case Neg:
                            value = anyType;
                            break;
                        case Zero:
                        case Pos:
                            value = AbstractVal.Neg;
                            break;
                    }
                }
                break;
            case TIMES:
                if (expr1Val == AbstractVal.Zero || expr2Val == AbstractVal.Zero) {
                    value = AbstractVal.Zero;
                } else if (hasAny) {
                    value = anyType;
                } else if (expr1Val == expr2Val) {
                    value = AbstractVal.Pos;
                } else if (posAndNeg) {
                    value = AbstractVal.Neg;
                }
                break;
            case DIV:
                // Conservative approach, NOT LESS conservative
                if (exprType == Type.INT) {
                    if (expr2Val == AbstractVal.Zero || expr2Val == AbstractVal.AnyInt) {
                        Interpreter.fatalError("Undefined operation, may div by 0", Interpreter.EXIT_DIV_BY_ZERO_ERROR);
                    } else if (expr1Val == AbstractVal.Zero) {
                        value = AbstractVal.Zero;
                    } else {
                        value = AbstractVal.AnyInt;
                    }
                } else if (exprType == Type.FLOAT) {
                    if (expr2Val == AbstractVal.Zero || expr2Val == AbstractVal.AnyFloat) {
                        Interpreter.fatalError("Undefined operation, may div by 0", Interpreter.EXIT_DIV_BY_ZERO_ERROR);
                    } else if (expr1Val == AbstractVal.Zero) {
                        value = AbstractVal.Zero;
                    } else if (posAndNeg) {
                        value = AbstractVal.Neg;
                    } else if (expr1Val == expr2Val) {
                        value = AbstractVal.Pos;
                    } else {
                        value = AbstractVal.AnyFloat;
                    }
                }
                break;
        }
    }
}

