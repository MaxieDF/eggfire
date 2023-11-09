package me.max.eggfire.Commands;

import me.max.eggfire.Commands.impl.ConfigCommand;
import me.max.eggfire.Eggfire;
import org.bukkit.command.CommandExecutor;

public class CommandRegistry {
    public static void registerCommands(){
        registerCommand("config", new ConfigCommand());
    }
    @SuppressWarnings("ConstantConditions")
    private static void registerCommand(String name, CommandExecutor command){
        Eggfire.getPlugin().getCommand(name).setExecutor(command);
    }
}
