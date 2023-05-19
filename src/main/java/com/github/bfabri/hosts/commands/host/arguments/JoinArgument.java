package com.github.bfabri.hosts.commands.host.arguments;

import com.github.bfabri.hosts.ConfigHandler;
import com.github.bfabri.hosts.Hosts;
import com.github.bfabri.hosts.commands.utils.CommandArgument;
import com.github.bfabri.hosts.events.HostJoinEvent;
import com.github.bfabri.hosts.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class JoinArgument extends CommandArgument {
    public JoinArgument() {
        super("join", "Join a game");
        this.onlyPlayer = true;
    }

    @Override
    public String getUsage(String label) {
        return '/' + label + ' ' + this.getName();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Hosts.getInstance().getGameManager().isGameAvailable()) {
            Bukkit.getPluginManager().callEvent(new HostJoinEvent((Player) sender, Hosts.getInstance().getGameManager().getGame()));
        } else {
            sender.sendMessage(Utils.PREFIX + Utils.translate(ConfigHandler.Configs.LANG.getConfig().getString("GAME-OFFLINE")));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
