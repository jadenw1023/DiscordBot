package com.experiment;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        // runs bot accepts token
        final String TOKEN = Tokens.botToken;
        JDABuilder jdaBuilder = JDABuilder.createDefault(TOKEN);
        JDA jda = jdaBuilder
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                        .addEventListeners(new ReadyListener(), new MessageListener(), new Listener() {
                        })
                                .build();


        jda.upsertCommand("slash-cmd", "This is a slash command").setGuildOnly(true).queue();
        jda.upsertCommand("hi", "Hello").setGuildOnly(true).queue();



    }
}