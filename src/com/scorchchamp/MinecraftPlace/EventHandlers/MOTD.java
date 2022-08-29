package com.scorchchamp.MinecraftPlace.EventHandlers;

import com.scorchchamp.MinecraftPlace.MinecraftPlace;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTD implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        if (MinecraftPlace.running) event.setMotd(ChatColor.YELLOW + "Minecraft Place running!\n" + ChatColor.AQUA + "Make sure to get your blocks in!");
    }
}
