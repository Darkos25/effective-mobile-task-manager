package com.example.EffectiveMobile.Model;

public enum Status {

    PENDING("в ожидании"),
    IN_PROGRESS("в процессе"),
    COMPLETED("завершено");

    private String title;

    Status(String title) {
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
