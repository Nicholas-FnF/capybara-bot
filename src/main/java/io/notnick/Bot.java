package io.notnick;

import net.dv8tion.jda.api.requests.GatewayIntent;
import io.notnick.event.listeners.ReadyListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import io.notnick.commands.Capy;
import io.notnick.commands.Fact;

public class Bot {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        // Get the value from DISCORD_TOKEN
        String token = dotenv.get("DISCORD_TOKEN");

        JDABuilder builder = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT);

        builder.addEventListeners(
                new ReadyListener(),
                new Capy(),
                new Fact()
        );

        builder.build();
    }
}
