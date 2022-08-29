package com.scorchchamp.MinecraftPlace;
import com.scorchchamp.MinecraftPlace.CommandHandlers.Setup;
import com.scorchchamp.MinecraftPlace.CommandHandlers.Start;
import com.scorchchamp.MinecraftPlace.EventHandlers.BlockPlaced;
import com.scorchchamp.MinecraftPlace.EventHandlers.MOTD;
import com.scorchchamp.MinecraftPlace.EventHandlers.BlockOtherEvents;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftPlace extends JavaPlugin {
    public static JavaPlugin instance;
    public static boolean running = false;
    public static PluginManager pm;
    public static CanvasData data;
    public static int place_rate = 5; // How many seconds between placements

    @Override
    public void onEnable() {
        instance = this;
        pm = instance.getServer().getPluginManager();
        data = new CanvasData();
        getLogger().info("MinecraftPlace enabled!");

        pm.registerEvents(new MOTD(), instance);
        pm.registerEvents(new BlockPlaced(), instance);
        pm.registerEvents(new BlockOtherEvents(), instance);

        instance.getServer().setWhitelist(false);
        getCommand("setup").setExecutor(new Setup());
        getCommand("start").setExecutor(new Start());
    }

    @Override
    public void onDisable() {
        getLogger().info("MinecraftPlace disabled!");
        instance.getServer().setWhitelist(true);
        data.save();
        for (Player p : instance.getServer().getOnlinePlayers()) {
            p.kickPlayer(ChatColor.RED + "Canvas plugin shutting down! Please wait until its enabled again!");
        }
    }
}
