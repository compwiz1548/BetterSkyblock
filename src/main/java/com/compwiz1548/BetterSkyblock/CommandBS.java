package com.compwiz1548.BetterSkyblock;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.compwiz1548.BetterSkyblock.Main.getUUIDs;
import static com.compwiz1548.BetterSkyblock.Main.plugin;

public class CommandBS implements CommandExecutor
{
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (args.length > 0)
        {
            if (commandSender instanceof Player)
            {
                if (args[0].equalsIgnoreCase("create"))
                {
                    if (getUUIDs().contains(((Player) commandSender).getUniqueId()))
                    {
                        commandSender.sendMessage("Island already created! Use /bs home to go to your island!");
                    }
                    else
                    {
                        boolean op = commandSender.isOp();
                        commandSender.setOp(true);
                        Bukkit.dispatchCommand(commandSender, "island create " + ((Player) commandSender).getDisplayName().toLowerCase());
                        plugin.addUUID(((Player) commandSender).getUniqueId());
                        Bukkit.getLogger().info("Created island for " + ((Player) commandSender).getUniqueId());
                        Bukkit.dispatchCommand(commandSender, "island join " + ((Player) commandSender).getDisplayName().toLowerCase());
                        commandSender.setOp(op);
                    }
                }
                else if (args[0].equalsIgnoreCase("visit"))
                {
                    if (args.length > 1)
                    {
                        if (!getUUIDs().contains(((Player) commandSender).getUniqueId()))
                            commandSender.sendMessage("Island doesn't exist!");
                        else
                        {
                            boolean op = commandSender.isOp();
                            commandSender.setOp(true);
                            Bukkit.dispatchCommand(commandSender, "island join " + args[1].toLowerCase());
                            commandSender.setOp(op);
                            commandSender.sendMessage("Teleported to " + args[1].toLowerCase());
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else if (args[0].equalsIgnoreCase("home"))
                {
                    if (getUUIDs().indexOf(((Player) commandSender).getUniqueId()) == -1)
                        commandSender.sendMessage("Island doesn't exist! Use /bs create to create your island!");
                    else
                    {
                        boolean op = commandSender.isOp();
                        commandSender.setOp(true);
                        Bukkit.dispatchCommand(commandSender, "island join " + ((Player) commandSender).getDisplayName().toLowerCase());
                        commandSender.setOp(op);
                    }
                }
                else if (args[0].equalsIgnoreCase("setspawn"))
                {
                    if (args.length == 1)
                    {
//                        Location loc = ((Player) commandSender).getLocation();
//                        boolean op = commandSender.isOp();
//                        commandSender.setOp(true);
//                        Bukkit.dispatchCommand(commandSender, "island setspawn " + ((Player) commandSender).getDisplayName() + " " + loc.getX() + " " + loc.getY() + " " + loc.getZ());
//                        commandSender.setOp(op);
//                        commandSender.sendMessage("Spawn set to X:" + loc.getX() + " Y:" + loc.getY() + " Z:" + loc.getZ());
                        commandSender.sendMessage("Not implemented yet!");
                    }
                    else if (args.length == 4)
                    {
//                        boolean op = commandSender.isOp();
//                        commandSender.setOp(true);
//                        Bukkit.dispatchCommand(commandSender, "island setspawn " + ((Player) commandSender).getDisplayName() + " " + args[1] + " " + args[2] + " " + args[3]);
//                        commandSender.setOp(op);
//                        commandSender.sendMessage("Spawn set to X:" + args[1] + " Y:" + args[2] + " Z:" + args[3]);
                        commandSender.sendMessage("Not implemented yet!");
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
        return true;
    }
}
