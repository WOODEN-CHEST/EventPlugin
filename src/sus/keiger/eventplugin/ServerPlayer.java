package sus.keiger.eventplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

public class ServerPlayer implements IServerPlayer
{
    // Private static fields.
    private static final String[] _edgyBoundLeavingMessages = { "Please do not leave the lobby's bounds.",
            "You are stuck here.", "Can't escape", "Sorry, but you can't leave.", "Invalid position!",
            "You moved too far away.", "Who told you that you can leave?", "Don't leave the lobby.",
            "Stop trying to escape.", "Incorrect position, sending back to spawn."};


    // Private fields.
    private final Player _player;
    private boolean _isInGame;
    private boolean _isHost;


    // Constructors.
    public ServerPlayer(Player player)
    {
        if (player == null)
        {
            throw new IllegalArgumentException("player is null");
        }
        _player = player;
    }


    // Methods.
    @SuppressWarnings("ConstantConditions")
    public void SetToLobby()
    {
        _player.setGameMode(GameMode.ADVENTURE);
        _player.setHealth(_player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        _player.setFireTicks(0);
        _player.setVelocity(new Vector(0d, 0d, 0d));
        _player.setInvulnerable(true);
        _player.setInvisible(false);
        _player.setGlowing(false);
        _player.setExp(0f);
        _player.setLevel(0);
        SetFoodInLobby();
        _player.teleport(EventPlugin.GetPlugin().GetServerManager().GetLobbyLocation());
    }


    // Private methods.
    private void SetFoodInLobby()
    {
        _player.setFoodLevel(20);
        _player.setSaturation(5f);
    }

    private void NonHostTick()
    {
        EnsureInLobbyBounds();
    }

    private void EnsureInLobbyBounds()
    {
        BoundingBox Bounds = EventPlugin.GetPlugin().GetServerManager().GetLobbyBounds();
        if (Bounds == null)
        {
            return;
        }
        Location PlayerLocation = _player.getLocation();
        if (!Bounds.contains(PlayerLocation.getX(), PlayerLocation.getY(), PlayerLocation.getZ()))
        {
            _player.teleport(EventPlugin.GetPlugin().GetServerManager().GetLobbyLocation());
            _player.sendMessage(Component.text(
                    _edgyBoundLeavingMessages[EventPlugin.GetPlugin().GetRandom()
                            .nextInt(0, _edgyBoundLeavingMessages.length)]).color(NamedTextColor.RED));
        }
    }


    // Inherited methods.
    @Override
    public void Tick()
    {
        if (_isInGame)
        {
            return;
        }

        SetFoodInLobby();
        if (!_isHost)
        {
            NonHostTick();
        }
    }

    @Override
    public Player GetPlayer()
    {
        return _player;
    }

    @Override
    public void OnJoinGameEvent()
    {
        _isInGame = true;
    }

    @Override
    public void OnExitGameEvent()
    {
        if (_isInGame)
        {
            SetToLobby();
        }
        _isInGame = false;
    }

    @Override
    public boolean GetIsHost()
    {
        return _isHost;
    }

    @Override
    public void SetIsHost(boolean value)
    {
        _isHost = value;
    }
}