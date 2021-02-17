// Created by: Exploitz#0169
package handlers;

import events.MessageEvent;
import main.Constants;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

// Extend JDA ListenerAdapter to inherit methods
public class EventHandler extends ListenerAdapter {

    private CommandHandler commandHandler;

    public EventHandler (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    // JDA MessageReceivedEvent - called whenever bot receives message either in
    // discord text channel or private dm
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        // This is the channel the message was sent in, either textchannel or private
        MessageChannel channel = event.getChannel();

        // If ignoreAllDm option is set to true and message is from DM, ignore the message
        if (Constants.ignoreAllDm && channel.getType().toString().equals("PRIVATE")) return;

        // If ignoreBotMessages option is set to true and the author is a bot, ignore the message
        if (Constants.ignoreBotMessages && event.getAuthor().isBot()) return;


        // Handle our message in appropriate event class
        MessageEvent.onMessage(event, this.commandHandler);
    }
}
