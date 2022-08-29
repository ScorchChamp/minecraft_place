package com.scorchchamp.MinecraftPlace.EventHandlers;

import com.scorchchamp.MinecraftPlace.MinecraftPlace;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaced implements Listener {
    @EventHandler
    public void blockPlaced(BlockPlaceEvent event) {
        if (!MinecraftPlace.running) {
            if (!event.getPlayer().hasPermission("MinecraftPlace.override")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "The event is currently not running!");
            }
            return;
        }
        Block block = event.getBlockAgainst();
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        if (x < MinecraftPlace.data.corner1().getX()) event.setCancelled(true);
        if (x > MinecraftPlace.data.corner2().getX()) event.setCancelled(true);
        if (y < MinecraftPlace.data.corner1().getY()) event.setCancelled(true);
        if (y > MinecraftPlace.data.corner2().getY()) event.setCancelled(true);
        if (z < MinecraftPlace.data.corner1().getZ()) event.setCancelled(true);
        if (z > MinecraftPlace.data.corner2().getZ()) event.setCancelled(true);
        if (event.isCancelled()) {
            event.getPlayer().sendMessage(ChatColor.RED + "Please only build inside the canvas!");
            return;
        }
        long next_time = MinecraftPlace.data.timeToNextPlace(event.getPlayer());
        if (next_time > 0) {
            event.getPlayer().sendMessage(ChatColor.RED + "Please wait " + (next_time + 1)/1000 + " seconds until you can build again!");
            event.setCancelled(true);
            return;
        }

        if (!event.getBlock().getType().toString().endsWith("CONCRETE")) {
            event.getPlayer().sendMessage(ChatColor.RED + "You can currently only build with concrete blocks!");
            event.setCancelled(true);

            return;
        }

        event.setCancelled(true);
        block.setType(event.getBlock().getType());
        event.getPlayer().sendMessage(ChatColor.GREEN + "Block placed!");
        event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5F, 3.0F);
        MinecraftPlace.data.setLastPlacement(event.getPlayer());
    }
}
