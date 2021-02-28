// Created by: Exploitz#0169
package commands.fun;

import Mitsuku.MitsukuChatSession;
import Mitsuku.MitsukuRequest;
import commands.meta.BaseCommand;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelloCommand implements BaseCommand {
    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public String getCategory() {
        return "fun";
    }

    @Override
    public void onCommand(MessageReceivedEvent event, MessageChannel channel, String[] args) {

        // If the bot is already active for someone else, return.
        // This is meant to avoid making too many web requests
        if (MitsukuChatSession.active) {
            channel.sendMessage("Mitsuku is already active, please wait your turn.").queue();
            return;
        }

        // Send message notifying user session has started
        channel.sendMessage("Starting chat session with Mitsuku. Type exit() to quit.").queue();

        // Create a new MitsukuChatSession
        event.getJDA().addEventListener(new MitsukuChatSession(event));

    }
}
