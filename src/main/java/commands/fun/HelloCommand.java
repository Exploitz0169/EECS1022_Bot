// Created by: Exploitz#0169
package commands.fun;

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

        channel.sendMessage("Fuck you.").queue();

    }
}
