// Created by: Exploitz#0169
package commands.meta;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

// Command Inteface
public interface BaseCommand {

    // Required method - command name
    public String getName ();

    // Optional method - aliases of command
    default public String[] getAliases () {
        String[] aliases = {};
        return aliases;
    }

    // Optional method - permissions required
    default public Permission[] getPermissions () {
        Permission[] permissions = {};
        return permissions;
    }

    default public int getCooldown () {
        return 0;
    }

    default public String getCategory () {
        return "general";
    }

    // Required method - method ran when command is called
    public void onCommand (MessageReceivedEvent event, MessageChannel channel, String[] args);

}
