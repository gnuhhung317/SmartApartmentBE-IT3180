package com.hust.smart_apartment.constants;


@SuppressWarnings("java:S1700")
public enum Operator {
    INCLUDE("include"),
    IN("in"),
    NIN("nin"),
    EQUAL("eq"),
    LIKE("like"),
    NOT_EQUAL("ne"),
    GREATER_THAN("gt"),
    LESS_THAN("lt"),
    GREATER_THAN_OR_EQUAL("gte"),
    LESS_THAN_OR_EQUAL("lte"),
    IS_NULL("is_null"),
    IS_NOT_NULL("is_not_null"),
    NONE("none");

    private final String operator;

    Operator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static Operator from(String operator) {
        for (Operator status : values()) {
            if (status.getOperator().equals(operator))
                return status;
        }
        return NONE;
    }
}

