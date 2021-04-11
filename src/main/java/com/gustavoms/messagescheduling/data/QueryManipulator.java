package com.gustavoms.messagescheduling.data;

public class QueryManipulator {

    public static String whereOrAnd(StringBuilder params) {
        if (params.toString().isEmpty()) {
            return "WHERE ";
        }

        return "AND ";
    }
}
