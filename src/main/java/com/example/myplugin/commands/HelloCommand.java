package com.example.myplugin.commands;

import javax.annotation.Nonnull;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.entities.Player;

public class HelloCommand extends CommandBase {

    public HelloCommand() {
        super("hello", "Hello world!");
    }

    @Override
    protected void executeSync(@Nonnull CommandContext commandContext) {
        Player player = commandContext.senderAs(Player.class);
        player.sendMessage(Message.raw("Hello world!"));
    }

}
