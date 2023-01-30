package com.experiment;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class ReadyListener implements EventListener {

    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready");
    }

  public void sendMessage(@NotNull MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String message = event.getMessage().getContentRaw();
            event.getChannel().sendMessage("Hi").queue();
        }
  }
}