package me.max.eggfire.events;

import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.external.JDAWebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import io.papermc.paper.event.player.AsyncChatEvent;
import me.max.eggfire.Eggfire;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.entities.WebhookClient;
import net.dv8tion.jda.internal.entities.WebhookImpl;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Set;

public class PlayerEventTracker implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncChatEvent event){
        String name = event.getPlayer().getName();
        String legText;
        legText = LegacyComponentSerializer.legacyAmpersand().serialize(event.message());
        Set<Audience> plist = event.viewers();
        String format = name + ": " + legText;
        TextComponent.Builder chat;
        chat = Component.text().append(MiniMessage.miniMessage().deserialize(format));
        for (Audience audience : plist) {
            audience.sendMessage(chat.build());
        }
        event.viewers().clear();
        String url = Eggfire.getPlugin().getConfig().getString("webhook-url");
        if(url.equals("DISABLED") || url.equals("DEFAULT")){
            return;
        }

        WebhookClientBuilder builder = new WebhookClientBuilder(url);
        builder.setThreadFactory((job) -> {
            Thread thread = new Thread(job);
            thread.setName("eggfire");
            thread.setDaemon(true);
            return thread;
        });
        builder.setWait(true);
        JDAWebhookClient client = builder.buildJDA();
        WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();
        messageBuilder.setUsername(name);
        messageBuilder.setAvatarUrl("https://mc-heads.net/head/" + name);
        messageBuilder.setContent(legText);
        client.send(messageBuilder.build());

    }
}
