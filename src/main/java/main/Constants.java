// Created by: Exploitz#0169
package main;

public class Constants {

    // Discord bot account token
    public static final String token = "ODExMjk5NjI2NTUyNTI0ODMy.YCwLqA.0DBZ7tOyPwajk9np6j0upzDmmQk";

    // Command prefix
    public static final String commandPrefix = "?";

    // Can ping the bot instead of using prefix to run commands
    public static final boolean useMentionAsPrefix = true;

    // Bot will ignore all messages sent to DM if true
    public static final boolean ignoreAllDm = true;

    // Bot will ignore all messages sent from any bot account
    public static final boolean ignoreBotMessages = true;

    // Bot will enforce command cooldowns if set to true
    public static final boolean enableCommandCooldowns = true;

    // Message sent to channel when user is on cooldown
    public static final String cooldownMessage = "{userMention} you must wait {time} before you can use this command again.";

    // If command is not found, send message
    public static final boolean sendCommandNotFoundMessage = true;

    // Command not found message
    public static final String commandNotFoundMessage = "{username}, that command was not found.";

    // Message sent if user does not has permissions for command
    public static final String noPermissionsMessage = "{userMention} you do not has permissions to use this command!";

    // Command categories that will show in help command
    public static final String[] categories = {"general", "moderation", "fun"};

}
