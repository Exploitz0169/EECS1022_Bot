// Created by: Exploitz#0169
package commands.general;

import commands.meta.BaseCommand;
import handlers.CommandHandler;
import main.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.stream.Collectors;

public class HelpCommand implements BaseCommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void onCommand(MessageReceivedEvent event, MessageChannel channel, String[] args) {

        // Get all supported command categories
        String[] categories = Constants.categories;
        // New EmbedBuilder instance
        EmbedBuilder embed = new EmbedBuilder();

        // Loop all categories
        for (String category : categories) {

            // Filter the commands for each category
            String filtered = CommandHandler.commands
                                                // Create set from map
                                                .entrySet()
                                                // Convert set to stream
                                                .stream()
                                                // Filter values in set. Boolean condition
                                                .filter(x -> x.getValue().getCategory().equals(category))
                                                // Map all the filtered values by their command name
                                                .map(x -> x.getValue().getName())
                                                // Collect values and join them with a comma
                                                .collect(Collectors.joining(", "));
            // If there are no commands in this category continue
            if (filtered.equals("")) continue;
            // Add embed field
            embed.addField(category.toUpperCase(), filtered, false);

        }

        // Build embed and send to channel
        channel
                .sendMessage(embed.build())
                .queue();

    }

}
