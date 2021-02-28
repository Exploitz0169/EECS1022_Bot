// Created by: Exploitz#0169
package main;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class Utils {

    // Method to format string to send to discod channel
    public static String formatMessage (String message, MessageReceivedEvent event) {
        return Utils.formatMessage(message, event, 0);
    }

    public static String formatMessage (String message, MessageReceivedEvent event, long time) {

        return message
                .replace("{userMention}", String.format("<@%s>", event.getAuthor().getId()))
                .replace("{username}", event.getAuthor().getName())
                .replace("{time}", Utils.milliToString(time));

    }

    public static String milliToString (long mils) {

        int toSeconds = (int) (mils / 1000);

        int days = toSeconds / 86400;
        int hours = (toSeconds % 86400 ) / 3600;
        int minutes = ((toSeconds % 86400 ) % 3600 ) / 60;
        int seconds = ((toSeconds % 86400 ) % 3600 ) % 60;

        String dayMessage = days > 1 ? " days " : " day ";
        String hourMessage = hours > 1 ? " hours " : " hour ";
        String minuteMessage = minutes > 1 ? " minutes " : " minute ";
        String secondMessage = seconds > 1 ? " seconds " : " second ";


        return "" + (days > 0 ? days + dayMessage : "")
                + (hours > 0 ? hours  +  hourMessage : "")
                + (minutes > 0 ? minutes + minuteMessage : "")
                + (seconds + secondMessage).trim();

    }


    public static JSONObject toJSON (String jsonString) {
        return new JSONObject(jsonString);
    }

    public static String formatMitsukuMessage (String message) {

        return message.replace("<image>https://web23.secure-secure.co.uk/square-bear.co.uk/pandorabots/giphylogo.png</image>", "")
                        .replace("<image>", "")
                        .replace("</image>", "")
                        .replace("„", " ");

    }


}
