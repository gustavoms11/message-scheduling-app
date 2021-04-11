package com.gustavoms.messagescheduling.data;

import java.util.Set;

public class QueryManipulator {

    public static String whereOrAnd(StringBuilder params) {
        if (params.toString().isEmpty()) {
            return "WHERE ";
        }

        return "AND ";
    }

    public static boolean isValidOrder(String order,
                                      Set<String> validOrders) {

        var checkOrder = order;
        if (checkOrder.startsWith("-") && checkOrder.length() > 1) {
            checkOrder = checkOrder.substring(1);
        }

        return validOrders.contains(checkOrder);
    }

    public static String ascOrDesc(String order) {
        if (order.startsWith("-")) {
            return "DESC";
        }

        return "ASC";
    }

    public static String extractOrder(String order) {
        var checkOrder = order;

        if (checkOrder.startsWith("-")) {
            return checkOrder.substring(1);
        }

        return checkOrder;
    }
}
