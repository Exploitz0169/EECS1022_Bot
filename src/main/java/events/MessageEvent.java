// Created by: Exploitz#0169
package events;

import handlers.CommandHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageEvent {

    public static void onMessage (MessageReceivedEvent event, CommandHandler commandHandler) {

        // Run command handler
        commandHandler.run(event);



    }

}
