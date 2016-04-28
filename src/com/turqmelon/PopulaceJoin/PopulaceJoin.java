package com.turqmelon.PopulaceJoin;


import com.turqmelon.Populace.Resident.Resident;
import com.turqmelon.Populace.Resident.ResidentManager;
import com.turqmelon.Populace.Town.TownRank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PopulaceJoin extends JavaPlugin implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onKick(PlayerKickEvent event){
        event.setLeaveMessage(null);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Resident resident = ResidentManager.getResident(player);
        event.setQuitMessage(null);
        if (resident != null && resident.getTown() != null){
            resident.getTown().sendTownBroadcast(TownRank.RESIDENT, resident.getTown().getRank(resident).getPrefix() + resident.getName() + "§d left the game.");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Resident resident = ResidentManager.getResident(player);
        event.setJoinMessage(null);
        if (resident != null && resident.getTown() != null){
            resident.getTown().sendTownBroadcast(TownRank.RESIDENT, resident.getTown().getRank(resident).getPrefix() + resident.getName() + "§d joined the game.");
        }
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
}
