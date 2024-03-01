package com.travelmate.model;

public enum PassengerType {
    STANDARD("Standard"), GOLD("Gold"), PREMIUM("Premium");

    private final String typeName;

    // Constructor
    private PassengerType(String typeName) {
        this.typeName = typeName;
    }

    // Getter method for type name
    public String getTypeName() {
        return typeName;
    }
}
