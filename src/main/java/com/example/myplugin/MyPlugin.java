package com.example.myplugin;

import com.example.myplugin.commands.HelloCommand;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

public class MyPlugin extends JavaPlugin {
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public MyPlugin(JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello world!");
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Registering commands...");
        this.getCommandRegistry().registerCommand(new HelloCommand());

        LOGGER.atInfo().log("Registering event listeners...");
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, EventHandler::onPlayerReady);

        LOGGER.atInfo().log("Setup complete!");
    }

}
