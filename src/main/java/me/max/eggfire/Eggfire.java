package me.max.eggfire;

import me.max.eggfire.Commands.CommandRegistry;
import me.max.eggfire.Commands.impl.ConfigCommand;
import me.max.eggfire.DiscordBot.DiscordBot;
import me.max.eggfire.DiscordBot.ReceiveMessage;
import me.max.eggfire.events.PlayerEventTracker;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public final class Eggfire extends JavaPlugin {
    private String token = "";
    private static DiscordBot bot;
    public static JavaPlugin getPlugin(){
        return getPlugin(Eggfire.class);
    }
    public static DiscordBot getBot(){ return bot; }
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        FileConfiguration config = getConfig();
        token = config.getString("token");
        if(token.isEmpty() || token.equals("DEFAULT")){
            getLogger().severe("Please change the discord bot token and channel id in the config");
        }else{
            this.bot = new DiscordBot(token, getLogger());
        }

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new PlayerEventTracker(), Eggfire.getPlugin());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }
}
