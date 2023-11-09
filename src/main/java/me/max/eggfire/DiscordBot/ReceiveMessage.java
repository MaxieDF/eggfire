package me.max.eggfire.DiscordBot;

import me.max.eggfire.Eggfire;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.logging.Logger;

public class ReceiveMessage extends ListenerAdapter implements EventListener{
    public void onMessageReceived(MessageReceivedEvent e){
        FileConfiguration config = Eggfire.getPlugin().getConfig();
        Server server = Eggfire.getPlugin().getServer();
        Logger logger = Eggfire.getPlugin().getLogger();
        String id = config.getString("channel-id");
        if(e.getChannel().getId().equals(id) && !e.getAuthor().isBot()){
            @NotNull TextComponent component = Component.text(ChatColor.BLUE + e.getMember().getEffectiveName() + ChatColor.WHITE + ": " + e.getMessage().getContentRaw());
            server.sendMessage(component);
        }
    }
}
