package com.csc.booklibrary.persistence.interfaces;

public enum Type {
    PERSONAL(1, "personal use"), PROJECT(2, "project use"), SUGGESTION(3, "suggestion"), EXTRACOPY(4,
            "extra copy suggestion");

    private final int typeId;
    private final String typeName;

    private Type(final int typeId, final String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public static String getTypeFromId(int typeId) {
        for (Type type : Type.values()) {
            if (type.getTypeId() == typeId) {
                return type.getTypeName();
            }
        }
        return null;
    }

}
