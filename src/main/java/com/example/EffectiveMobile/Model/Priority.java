package com.example.EffectiveMobile.Model;

public enum Priority {

    HIGH("Высокий"),
    MEDIUM("Средний"),
    LOW("Низкий");

    private String title;

    Priority(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return  this.title;
    }
}
