package Mitsuku;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

public class MitsukuChatSession extends ListenerAdapter {

    public static boolean active = false;

    private MessageReceivedEvent parentEvent;

    private long channelID;
    private long authorID;

    private Timer timer = new Timer();

    public MitsukuChatSession (MessageReceivedEvent event) {

        this.parentEvent = event;
        this.channelID = event.getChannel().getIdLong();
        this.authorID = event.getAuthor().getIdLong();

        // Change mitsuku to active status
        MitsukuChatSession.active = true;

        // Create initial timer, even if user does not type to bot.
        createTimer();

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        User author = event.getAuthor();
        MessageChannel channel = event.getChannel();

        // If author is a bot or message is in a different channel or author is someone else, return
        if (author.isBot()) return;
        if (author.getIdLong() != authorID) return;
        if (channel.getIdLong() != channelID) return;

        String content = event.getMessage().getContentRaw();

        // Cancel existing timer
        timer.cancel();

        // If user wants to exit stop listening to messages
        // Change bot active to false
        if (content.toLowerCase().equals("exit()")) {
            channel.sendMessage("Goodbye.").queue();
            event.getJDA().removeEventListener(this);
            MitsukuChatSession.active = false;
            return;
        }

        // Create a new webrequest to Mitsuku chat bot
        channel.sendMessage(new MitsukuRequest(content).run()).queue();

        // Create a timer to quit if user does not respond
        createTimer();

    }

    private void createTimer() {

        // This is used to make the current instance of object available within timer
        MitsukuChatSession _this = this;

        // New timer object since you cannot restart timer
        timer = new Timer();

        // Timer task that is run
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Send message saying no messages have been received
                parentEvent.getChannel()
                        .sendMessage("Mitsuku has not received a message in 30 seconds....quitting.")
                        .queue();

                // Stop listening for new messages
                parentEvent.getJDA().removeEventListener(_this);
                // Cancel the timer
                timer.cancel();
                // Chat no longer active
                MitsukuChatSession.active = false;
            }
        };

        // Schedule task with 30 second delay
        timer.schedule(task, 30000);


    }

}

