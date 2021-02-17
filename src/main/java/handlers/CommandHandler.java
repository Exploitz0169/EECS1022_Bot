// Created by: Exploitz#0169
package handlers;

import commands.meta.BaseCommand;
import main.Constants;
import main.Utils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class CommandHandler {

    // Create key value pair for cooldowns
    // Cooldown will be named as 'userID_commandName'
    private HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    // HashMap of all instances of each command, key name is the command name
    public static HashMap<String, BaseCommand> commands = new HashMap<String, BaseCommand>();
    // Each alias has a reference to the command name
    private HashMap<String, String> aliasCommands = new HashMap<String, String>();

    // Command handler initialization boolean
    private boolean isInitialized = false;

    // Array of commands is provided
    public void init (BaseCommand[] commandList) {

        // If command handler is not initialized, return
        if (isInitialized) return;

        // Loop array of commands and set them in HashMap
        for (BaseCommand command : commandList) {
            commands.put(command.getName().toLowerCase(), command);
            // Create references to parent command name for each alias
            for (String alias : command.getAliases()) {
                aliasCommands.put(alias, command.getName().toLowerCase());
            }
        }

        // Set command handler to initialized
        isInitialized = true;

    }

    public void run (MessageReceivedEvent event) {

        // If command handler is not initialized, return
        if (!isInitialized) return;

        // Rae content of message sent in discord
        String messageContent = event.getMessage().getContentRaw();

        // Check if the bot was pinged
        boolean botPinged = checkBotPing(event);

        // Check if the message the user sent was meant to be a command for the bot
        if (messageContent.startsWith(Constants.commandPrefix) || (Constants.useMentionAsPrefix && botPinged)) {

            // Extract the command name from the message
            String commandName = extractCommandName(botPinged, messageContent).toLowerCase();

            // Message channel object of where message was sent
            MessageChannel channel = event.getChannel();

            // Get arguments of message
            String[] args = getArgs(botPinged, commandName, messageContent);

            // If there was no command, return
            if(commandName.equals("")) return;

            // Try to find command in HashMap
            BaseCommand command = commands.get(commandName);

            // If command not found, try to find command in HashMap using reference from alias commands
            if (command == null) {
                command = commands.get(aliasCommands.get(commandName));
            }


            // If the command is not found in our hashmap
            if (command == null) {
                if (Constants.sendCommandNotFoundMessage) {
                    channel
                            .sendMessage(Utils.formatMessage(Constants.commandNotFoundMessage, event))
                            .queue();
                    return;
                }
                return;
            }



            // Get player member object, will be null if player is in dm
            Member member = event.getMember();

            // Check if member has perms to run this command
            boolean hasPerms = hasPermissions(command.getPermissions(), member);

            // If they dont have permissions, send a message
            if (!hasPerms) {
                channel
                        .sendMessage(Utils.formatMessage(Constants.noPermissionsMessage, event))
                        .queue();
                return;
            }

            // If command cooldowns are enabled
            if (Constants.enableCommandCooldowns) {

                // Get cooldown from command in seconds
                int commandCooldown = command.getCooldown();

                // Skip all of this if the command does not have a cooldown
                if (commandCooldown != 0) {
                    // Get cooldown from hashmap
                    Long cooldown = cooldowns.get(String.format("%s_%s", event.getAuthor().getId(), commandName));
                    // Get current time in milliseconds
                    long currentMils = new Date().getTime();

                    // If there is a cooldown
                    if (cooldown != null) {
                        // If there is still time left on the command cooldown, then send message and return
                        if (cooldown - currentMils > 0) {
                            channel
                                    .sendMessage(Utils.formatMessage(Constants.cooldownMessage, event, cooldown - currentMils))
                                    .queue();
                            return;
                        }

                    }

                    // Update hashmap with cooldown for user
                    cooldowns.put(String.format("%s_%s", event.getAuthor().getId(), commandName), currentMils + commandCooldown * 1000);
                }

            }



            // Finally run the command
            command.onCommand(event, channel, args);


        }

    }


    public boolean checkBotPing (MessageReceivedEvent event) {

        // Get bot id from JDA
        String botID = event.getJDA().getSelfUser().getId();
        // Get raw message content
        String messageContent = event.getMessage().getContentRaw().trim();

        // Get the first word in the mesasge
        String firstWord = messageContent.split(">")[0];

        // IDs are formatted as follows: <@id>. So if the message starts with a '<' and the extracted ID
        // matches the bots then return true
        if (messageContent.startsWith("<") && extractInt(firstWord).equals(botID)) {
            return true;
        } else {
            return false;
        }
    }

    public String extractInt (String str) {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^\\d]", " ");

        // Remove extra spaces from the beginning
        // and the ending of the string
        str = str.trim();

        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", " ");

        if (str.equals(""))
            return "-1";

        return str;
    }


    public String extractCommandName (boolean botPinged, String message) {

        // If bot was not pinged then split at each whitespace, get first result,
        // trim the prefix length
        if (!botPinged) {
            try {
                return message.split("\\s+")[0].substring(Constants.commandPrefix.length());
            } catch (Exception e) {
                // If there was an error, return empty string
                return "";
            }
        }

        try {
            // If bot was pinged then split at the end of ID, trim, split at each whitespace and get first result
            return message.split(">")[1].trim().split("\\s+")[0];

        } catch (Exception e) {
            // If error occurs, return empty string
            return "";

        }

    }

    public String[] getArgs (boolean botPinged, String commandName, String message) {

        // If bot is not pinged trim prefix and command name length to get args
        if (!botPinged) {
            try {
                return message.substring(Constants.commandPrefix.length() + commandName.length())
                        .trim()
                        .split("\\s+");
            } catch (Exception e) {
                // Empty array if error occurs
                String[] args = {};
                return args;
            }
        }

        try {
            // If bot is pinged split at end of id then trim command name length to get args
            return message.split(">")[1]
                    .trim()
                    .substring(commandName.length())
                    .trim()
                    .split("\\s+");
        } catch (Exception e) {
            // Return empty array if error occurs
            String[] args = {};
            return args;

        }

    }


    public boolean hasPermissions(Permission[] permissions, Member member) {
        // Initially assume player has permissions
        boolean hasPerms = true;

        // If player does not have one of the permissions required, then they cannot run the command
        if (member != null) {
            for (Permission p : permissions) {
                if (!member.hasPermission(p)) hasPerms = false;
            }
        }

        return hasPerms;

    }


}
