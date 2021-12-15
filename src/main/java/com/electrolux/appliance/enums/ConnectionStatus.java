package com.electrolux.appliance.enums;

public enum ConnectionStatus {
    CONNECTED("CONNECTED"), DISCONNECTED("DISCONNECTED"), UNKNOWN("UNKNOWN");

    private String name;

    ConnectionStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
