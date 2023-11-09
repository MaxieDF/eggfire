package me.max.eggfire.Commands.impl;

import me.max.eggfire.Eggfire;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class ConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        FileConfiguration config = Eggfire.getPlugin().getConfig();
        sender.sendRichMessage(config.getString("token"));
        sender.sendRichMessage(config.getString("channel-id"));
        return true;
    }
}
