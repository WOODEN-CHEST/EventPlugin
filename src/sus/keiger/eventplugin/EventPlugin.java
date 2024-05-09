package sus.keiger.eventplugin;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class EventPlugin extends JavaPlugin
{
    // Private static fields.
    private static EventPlugin s_pluginInstance;


    // Private fields.
    private ServerManager _serverManager;
    private final Random _rng;


    // Constructors.
    public EventPlugin()
    {
        _rng = new Random();
    }


    // Static methods.
    public static EventPlugin GetPlugin()
    {
        return s_pluginInstance;
    }


    // Methods.
    public ServerManager GetServerManager()
    {
        return _serverManager;
    }

    public Random GetRandom()
    {
        return _rng;
    }


    // Inherited methods.
    @Override
    public void onEnable()
    {
        s_pluginInstance = this;

        _serverManager = new ServerManager();
        _serverManager.SetupGamerules();
    }
}