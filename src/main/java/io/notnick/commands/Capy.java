package io.notnick.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.EmbedBuilder;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.json.JSONObject;
import java.net.URL;
import java.awt.*;

public class Capy extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("capy")) return;

        try {
            String urlString = "https://api.capy.lol/v1/capybara?json=true";
            URL url = new URL(urlString);

            // Opens the connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request metadata
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "image/jpeg");

            if (connection.getResponseCode() != 200) {
                event.reply(connection.getResponseMessage()).setEphemeral(true).queue();
                throw new RuntimeException("Something went wrong.");
            }

            // Read the response
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder response = new StringBuilder();

            String output;

            while ((output = bufferedReader.readLine()) != null) {
                response.append(output);
            }

            // Parse json response
            JSONObject jsObject = new JSONObject(response.toString());

            // Get the specific key (which contains the actual image) in the json data
            String image = jsObject.getJSONObject("data").getString("url");

            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setTitle("Capybara 🦫");
            embedBuilder.setUrl(image);
            embedBuilder.setDescription("Here’s a cool picture of a capybara!");
            embedBuilder.setColor(Color.green);
            embedBuilder.setImage(image);
            embedBuilder.setFooter("capy.lol");

            event.replyEmbeds(embedBuilder.build()).queue();

            connection.disconnect();
        } catch (Exception e) {
            event.reply("Something went wrong").setEphemeral(true).queue();
        }
    }
}
