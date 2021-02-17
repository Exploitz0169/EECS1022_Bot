// File created by: Exploitz#0169

package main;

import handlers.CommandHandler;
import handlers.EventHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class EECSBot {

    // Handle LoginException
    public static void main(String[] args) throws LoginException {

        // Bot made for EECS 1022 discord server

        // When creating a command please leave your discord name + discriminator in the file
        // Example: Created by Exploitz#0169
        // Feel free to leave your full name as well

        // Comment your code and use readable variable names to ensure the learning of peers

        // Create instance of command handler
        CommandHandler commandHandler = new CommandHandler();
        // Initialize the command handler
        commandHandler.init(CommandList.commands);

        // Create JDA instance and login with discord bot token from constants
        JDA bot = JDABuilder.createDefault(Constants.token)
                // Add event handler to listen for events
                .addEventListeners(new EventHandler(commandHandler))
                // Build JDA instance and begin login process
                .build();




    }


}


