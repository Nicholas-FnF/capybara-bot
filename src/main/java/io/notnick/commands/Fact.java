package io.notnick.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import io.notnick.helpers.JsonReader;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Random;

public class Fact extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("fact")) return;

        String json = JsonReader.readResourceFile("facts.json");

        if (json != null) {
            JSONObject jsonArray = new JSONObject(json);

            // Access the facts array
            JSONArray facts = jsonArray.getJSONArray("facts");

            Random random = new Random();

            // Get a random index
            int randomIndex = random.nextInt(facts.length());

            // Get the fact at the random index
            JSONObject jsonObject = facts.getJSONObject(randomIndex);

            String fact = jsonObject.getString("fact")
                    .replaceAll("\\s*\\(variation \\d+\\)$", "");

            int id = jsonObject.getInt("id");

            event.reply("[" + id + "] " + fact).queue();
        }
    }
}
