package me.max.eggfire.Commands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.lang.annotation.Annotation;

public class CommandHandler implements EventHandler {
    @Override
    public EventPriority priority() {
        return null;
    }

    @Override
    public boolean ignoreCancelled() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
