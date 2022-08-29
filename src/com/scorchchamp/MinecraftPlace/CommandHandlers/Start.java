package com.scorchchamp.MinecraftPlace.CommandHandlers;

import com.scorchchamp.MinecraftPlace.MinecraftPlace;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Start implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (MinecraftPlace.running) {
            commandSender.sendMessage(ChatColor.RED + "The game is already running!");
            return true;
        }
        MinecraftPlace.running = true;
        MinecraftPlace.instance.getServer().broadcastMessage(ChatColor.GOLD + "\n\n\nTHE GAME HAS BEGUN!\n");
        for (Player p : MinecraftPlace.instance.getServer().getOnlinePlayers()) {
            p.playSound(p, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
        }
        return true;
    }
}
