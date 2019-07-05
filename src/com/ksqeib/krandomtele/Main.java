package com.ksqeib.krandomtele;

import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.kingdoms.constants.player.KingdomPlayer;
import org.kingdoms.manager.game.GameManagement;

import java.util.Random;


public class Main  extends JavaPlugin implements Listener {
    int f=3000;
    Random rm=new Random();
    @Override
    public void onEnable() {
        //事件
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e){
        if(e.getPlayer().getStatistic(Statistic.PLAY_ONE_TICK)==0){
            e.getPlayer().teleport(rtp(e.getPlayer()));
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerRespawnEvent e){
        KingdomPlayer kp=GameManagement.getPlayerManager().getSession(e.getPlayer());
        if(kp==null)return;
        if(kp.getKingdomName()!=null)return;
        e.setRespawnLocation(rtp(e.getPlayer()));
    }
    public Location rtp(Player p){
        World w=p.getWorld();
        double x=rm.nextDouble()*f;
        double z=rm.nextDouble()*f;
        Double y=(double) w.getHighestBlockYAt((int)x,(int)z);
        return new Location(w,x,y,z);
    }
}
