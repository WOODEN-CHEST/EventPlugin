package sus.keiger.eventplugin;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.*;
import org.bukkit.util.BoundingBox;
import sus.keiger.eventplugin.battlefield.BattlefieldManager;

import java.util.EventListener;
import java.util.HashMap;

public class ServerManager implements EventListener, ITickable
{
    // Private fields.
    private HashMap<Player, IServerPlayer> _players = new HashMap<>();
    private Location _lobbyLocation;
    private BoundingBox _lobbyBounds = new BoundingBox(-20d, -20d, -20d, 20d, 20d, 20d);
    private final World _overworld;
    private final World _nether;
    private final World _end;


    /* Games. */
    private final BattlefieldManager _battleFieldManager = new BattlefieldManager();


    // Constructors.
    public ServerManager()
    {
        _overworld = Bukkit.getWorld(new NamespacedKey(NamespacedKey.MINECRAFT, "overworld"));
        _nether = Bukkit.getWorld(new NamespacedKey(NamespacedKey.MINECRAFT, "the_nether"));
        _end = Bukkit.getWorld(new NamespacedKey(NamespacedKey.MINECRAFT, "the_end"));
        _lobbyLocation = new Location(_overworld, 0d, 0d, 0d, 0f, 0f);
    }


    // Methods.
    public void SetupGamerules()
    {
        for (World TargetWorld : Bukkit.getWorlds())
        {
            TargetWorld.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            TargetWorld.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
            TargetWorld.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, false);
            TargetWorld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            TargetWorld.setGameRule(GameRule.DO_ENTITY_DROPS, false);
            TargetWorld.setGameRule(GameRule.DO_FIRE_TICK, false);
            TargetWorld.setGameRule(GameRule.DO_LIMITED_CRAFTING, false);
            TargetWorld.setGameRule(GameRule.DO_MOB_LOOT, false);
            TargetWorld.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            TargetWorld.setGameRule(GameRule.DO_TILE_DROPS, false);
            TargetWorld.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            TargetWorld.setGameRule(GameRule.KEEP_INVENTORY, true);
            TargetWorld.setGameRule(GameRule.LOG_ADMIN_COMMANDS, true);
            TargetWorld.setGameRule(GameRule.MOB_GRIEFING, false);
            TargetWorld.setGameRule(GameRule.NATURAL_REGENERATION, true);
            TargetWorld.setGameRule(GameRule.REDUCED_DEBUG_INFO, false);
            TargetWorld.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, true);
            TargetWorld.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
            TargetWorld.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
            TargetWorld.setGameRule(GameRule.DISABLE_RAIDS, true);
            TargetWorld.setGameRule(GameRule.DO_INSOMNIA, false);
            TargetWorld.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
            TargetWorld.setGameRule(GameRule.DROWNING_DAMAGE, true);
            TargetWorld.setGameRule(GameRule.FALL_DAMAGE, true);
            TargetWorld.setGameRule(GameRule.FIRE_DAMAGE, true);
            TargetWorld.setGameRule(GameRule.FREEZE_DAMAGE, true);
            TargetWorld.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
            TargetWorld.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
            TargetWorld.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
            TargetWorld.setGameRule(GameRule.FORGIVE_DEAD_PLAYERS, true);
            TargetWorld.setGameRule(GameRule.UNIVERSAL_ANGER, false);
            TargetWorld.setGameRule(GameRule.BLOCK_EXPLOSION_DROP_DECAY, false);
            TargetWorld.setGameRule(GameRule.MOB_EXPLOSION_DROP_DECAY, true);
            TargetWorld.setGameRule(GameRule.TNT_EXPLOSION_DROP_DECAY, false);
            TargetWorld.setGameRule(GameRule.WATER_SOURCE_CONVERSION, true);
            TargetWorld.setGameRule(GameRule.LAVA_SOURCE_CONVERSION, false);
            TargetWorld.setGameRule(GameRule.GLOBAL_SOUND_EVENTS, true);
            TargetWorld.setGameRule(GameRule.DO_VINES_SPREAD, false);
            TargetWorld.setGameRule(GameRule.ENDER_PEARLS_VANISH_ON_DEATH, true);
            TargetWorld.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
            TargetWorld.setGameRule(GameRule.SPAWN_RADIUS, 0);
            TargetWorld.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 0);
            TargetWorld.setGameRule(GameRule.MAX_COMMAND_CHAIN_LENGTH, Integer.MAX_VALUE);
            TargetWorld.setGameRule(GameRule.COMMAND_MODIFICATION_BLOCK_LIMIT, (int)Short.MAX_VALUE);
            TargetWorld.setGameRule(GameRule.PLAYERS_SLEEPING_PERCENTAGE, 0);
            TargetWorld.setGameRule(GameRule.SNOW_ACCUMULATION_HEIGHT, 0);
        }
    }

    public Location GetLobbyLocation()
    {
        return _lobbyLocation;
    }

    public void SetLobbyLocation(Location location)
    {
        if (location == null)
        {
            throw new IllegalArgumentException("location is null");
        }
        _lobbyLocation = location.clone();
    }

    public BoundingBox GetLobbyBounds()
    {
        return _lobbyBounds;
    }

    public void SetLobbyBounds(BoundingBox bounds)
    {
        _lobbyBounds = bounds;
    }


    /* Events. */
    @EventHandler
    public void OnServerTickStartEvent(ServerTickStartEvent event)
    {
        Tick();
        for (IServerPlayer TargetPlayer : _players.values())
        {
            TargetPlayer.Tick();
        }
    }

    @EventHandler
    public void OnPlayerJoinEvent(PlayerJoinEvent event)
    {
        CreatePlayer(event.getPlayer());
    }

    @EventHandler
    public void OnPlayerQuitEvent(PlayerQuitEvent event)
    {
        RemovePlayer(event.getPlayer());
    }


    // Private methods.
    private void CreatePlayer(Player player)
    {
        _players.put(player, new ServerPlayer(player));
    }

    private void RemovePlayer(Player player)
    {
        _players.remove(player);
    }


    // Inherited methods.
    @Override
    public void Tick()
    {

    }
}
