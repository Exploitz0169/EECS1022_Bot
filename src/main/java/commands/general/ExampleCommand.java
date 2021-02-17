// Created by: Exploitz#0169

package commands.general;

import commands.meta.BaseCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ExampleCommand implements BaseCommand {

    // This method is mandatory
    @Override
    public String getName() {
        return "example";
    }

    // This method is optional, you may skip it
    @Override
    public int getCooldown() {
        return 10;
    }

    // This method is optional, you may skip it
    @Override
    public Permission[] getPermissions() {
        Permission[] permissions = {
                Permission.ADMINISTRATOR
        };
        return permissions;
    }

    @Override
    public void onCommand(MessageReceivedEvent event, MessageChannel channel, String[] args) {
        // Example command provided to send a message

        // Call sendMessage method on channel object to send a message to the channel where command was invoked
        channel
            .sendMessage("Haha what's up dude?")
            // Make sure to call queue method or nothing will happen
            .queue();

    }


}
