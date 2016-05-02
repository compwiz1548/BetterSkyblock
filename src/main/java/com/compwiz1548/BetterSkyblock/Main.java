package com.compwiz1548.BetterSkyblock;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin
{

    public static volatile Main plugin = null;
    public static Server server;
    public static ArrayList<UUID> uuids = new ArrayList<UUID>();
    public File uuidFile;

    public static ArrayList<UUID> getUUIDs()
    {
        return uuids;
    }

    @Override
    public void onEnable()
    {
        if (plugin == null)
        {
            plugin = this;
        }
        server = getServer();

        plugin.getCommand("bs").setExecutor(new CommandBS());

        if (!getDataFolder().exists())
        {
            getDataFolder().mkdirs();
        }
        uuidFile = new File(getDataFolder(), "uuids.txt");
        createFile();
        loadUUIDs(uuidFile);
    }

    @Override
    public void onDisable() { /*Nothing to do*/ }

    private void createFile()
    {
        try
        {
            if (!uuidFile.exists())
            {
                uuidFile.createNewFile();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void loadUUIDs(File uuidFile)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(uuidFile));

            String line = in.readLine();
            while (line != null)
            {
                uuids.add(UUID.fromString(line));
                line = in.readLine();
            }
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addUUID(UUID uuid)
    {
        uuids.add(uuid);
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(uuidFile, true));

            out.append(uuid.toString() + "\n");
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}