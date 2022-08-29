package com.scorchchamp.MinecraftPlace.CommandHandlers;

import com.scorchchamp.MinecraftPlace.MinecraftPlace;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Setup implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "This Command may only be run from a player!");
            return false;
        }

        if (MinecraftPlace.running) {
            commandSender.sendMessage(ChatColor.RED + "The event is currently running!");
            return true;
        }



        if (strings.length != 6) return false;
        int x1 = Integer.parseInt(strings[0]);
        int y1 = Integer.parseInt(strings[1]);
        int z1 = Integer.parseInt(strings[2]);
        int x2 = Integer.parseInt(strings[3]);
        int y2 = Integer.parseInt(strings[4]);
        int z2 = Integer.parseInt(strings[5]);
        World world = ((Player) commandSender).getLocation().getWorld();

        MinecraftPlace.data.setCorner1(new Location(world, Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2)));
        MinecraftPlace.data.setCorner2(new Location(world, Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2)));

        this.buildCorners();

        return true;
    }

    private void buildCorners() {
        Location corner1 = MinecraftPlace.data.corner1().subtract(1,0,1);
        Location corner2 = MinecraftPlace.data.corner2().add(1,0,1);

        for (int x = corner1.getBlockX(); x < corner2.getX(); x++) corner1.getWorld().getBlockAt(x, (int) corner1.getY(), (int) corner1.getZ()).setType(Material.BEDROCK);
        for (int x = corner1.getBlockX(); x < corner2.getX(); x++) corner1.getWorld().getBlockAt(x, (int) corner2.getY(), (int) corner1.getZ()).setType(Material.BEDROCK);
        for (int x = corner1.getBlockX(); x < corner2.getX()+1; x++) corner1.getWorld().getBlockAt(x, (int) corner1.getY(), (int) corner2.getZ()).setType(Material.BEDROCK);
        for (int x = corner1.getBlockX(); x < corner2.getX(); x++) corner1.getWorld().getBlockAt(x, (int) corner2.getY(), (int) corner2.getZ()).setType(Material.BEDROCK);


        for (int z = corner1.getBlockZ(); z < corner2.getZ(); z++) corner1.getWorld().getBlockAt((int) corner1.getX(), (int) corner1.getY(), z).setType(Material.BEDROCK);
        for (int z = corner1.getBlockZ(); z < corner2.getZ(); z++) corner1.getWorld().getBlockAt((int) corner2.getX(), (int) corner1.getY(), z).setType(Material.BEDROCK);
        for (int z = corner1.getBlockZ(); z < corner2.getZ(); z++) corner1.getWorld().getBlockAt((int) corner1.getX(), (int) corner2.getY(), z).setType(Material.BEDROCK);
        for (int z = corner1.getBlockZ(); z < corner2.getZ() + 1; z++) corner1.getWorld().getBlockAt((int) corner2.getX(), (int) corner2.getY(), z).setType(Material.BEDROCK);

        for (int x = corner1.getBlockX()+1; x < corner2.getX(); x++) {
            for (int z = corner1.getBlockZ()+1; z < corner2.getZ(); z++) {
                corner1.getWorld().getBlockAt(x, (int) corner1.getY(), z).setType(Material.WHITE_CONCRETE);
            }
        }
    }
}
