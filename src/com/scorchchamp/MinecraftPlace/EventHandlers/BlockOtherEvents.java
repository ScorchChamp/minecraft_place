package com.scorchchamp.MinecraftPlace.EventHandlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockOtherEvents implements Listener {
    @EventHandler
    public void preventBlockBreaking(BlockBreakEvent event) {
        if (!event.getPlayer().hasPermission("MinecraftPlace.override")) event.setCancelled(true);
    }

    @EventHandler
    public void preventItemUsage(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (!event.getPlayer().hasPermission("MinecraftPlace.override")) event.setCancelled(true);
    }
}
