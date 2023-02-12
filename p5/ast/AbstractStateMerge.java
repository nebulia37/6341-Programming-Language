package ast;

import interpreter.Interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AbstractStateMerge {
    public static void mergeState(Map<String, AbstractVal> valueTable, Map<String, AbstractVal> valueTableCopy, ArrayList<Map<String, Type>> typeMaps) {
        Map<String, Type> combinedTypeMap = new HashMap<>();
        for (Map<String, Type> s : typeMaps) {
            combinedTypeMap.putAll(s);
        }

        for (String key : valueTable.keySet()) {
            if (valueTableCopy.containsKey(key)) {
                if (valueTable.get(key) != valueTableCopy.get(key)) {
                    Type type;
                    type = combinedTypeMap.get(key);

                    if (type == null) {
                        Interpreter.fatalError("Not able to find the key in combinedStateMap", Interpreter.EXIT_UNINITIALIZED_VAR_ERROR);
                    } else if (type == Type.INT) {
                        valueTable.put(key, AbstractVal.AnyInt);
                    } else if (type == Type.FLOAT) {
                        valueTable.put(key, AbstractVal.AnyFloat);
                    }
                }
            } else {
                valueTable.remove(key);
            }
        }
    }
}
