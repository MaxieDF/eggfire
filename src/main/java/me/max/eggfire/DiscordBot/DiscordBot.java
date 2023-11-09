package me.max.eggfire.DiscordBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.logging.Logger;

public class DiscordBot{
    private JDA jda;
    private Logger logger;
    //private EmbedBuilder embedBuilder;
    public DiscordBot(String token, Logger logger){
        this.logger = logger;
        this.logger.info("Activating bot with token \"" + token + "\"");
        this.jda = JDABuilder.createDefault(token, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .setActivity(Activity.playing("eggfire"))
                .setStatus(OnlineStatus.ONLINE).build();
        jda.addEventListener(new ReceiveMessage());
    }

    public JDA getJda(){
        return this.jda;
    }
}
