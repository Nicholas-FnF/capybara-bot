package io.notnick.event.listeners;

import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("✅ Capybara is ready!");

        event.getJDA().updateCommands().addCommands(
                Commands.slash("fact", "Sends a random fact about capybaras."),
                Commands.slash("capy", "Sends a cute capybara picture.")
        ).queue();
    }
}
