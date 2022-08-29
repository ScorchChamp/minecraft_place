package com.scorchchamp.MinecraftPlace;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CanvasData {
    private File dataFile = new File(MinecraftPlace.instance.getDataFolder(), "data.yml");
    private FileConfiguration config;

    CanvasData() {
        load();
    }

    public Location corner1() {
        int x = config.getInt("corner1.x");
        int y = config.getInt("corner1.y");
        int z = config.getInt("corner1.z");
        World world = Bukkit.getWorld(config.getString("world.name"));
        try { return new Location(world, x, y, z); }
        catch (Exception e) { return null; }
    }

    public Location corner2() {
        int x = config.getInt("corner2.x");
        int y = config.getInt("corner2.y");
        int z = config.getInt("corner2.z");
        World world = Bukkit.getWorld(config.getString("world.name"));
        try { return new Location(world, x, y, z); }
        catch (Exception e) { return null; }
    }

    public void load() {
        this.config = YamlConfiguration.loadConfiguration(this.dataFile);
    }

    public void save() {
        try { this.config.save(dataFile); } catch (IOException e) { e.printStackTrace(); }
    }

    public void setCorner1(Location location) {
        this.config.set("world.name", location.getWorld().getName());
        this.config.set("corner1.x", location.getX());
        this.config.set("corner1.y", location.getY());
        this.config.set("corner1.z", location.getZ());
        this.save();
    }

    public void setCorner2(Location location) {
        this.config.set("world.name", location.getWorld().getName());
        this.config.set("corner2.x", location.getX());
        this.config.set("corner2.y", location.getY());
        this.config.set("corner2.z", location.getZ());
        this.save();
    }

    public long timeToNextPlace(Player p) {
        long last = this.config.getLong("last_placement." + p.getUniqueId());
        if (last == 0) return 0;
        return last - System.currentTimeMillis() + (MinecraftPlace.place_rate * 1000);
    }

    public void setLastPlacement(Player p) {
        this.config.set("last_placement." + p.getUniqueId(), System.currentTimeMillis());
        this.save();
    }
}
