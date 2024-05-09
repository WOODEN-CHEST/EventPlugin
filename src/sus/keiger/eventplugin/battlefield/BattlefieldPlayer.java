package sus.keiger.eventplugin.battlefield;

import org.bukkit.entity.Player;
import sus.keiger.eventplugin.IServerPlayer;
import sus.keiger.eventplugin.game.GameTeam;
import sus.keiger.eventplugin.game.IGame;
import sus.keiger.eventplugin.game.IGamePlayer;

public class BattlefieldPlayer implements IGamePlayer
{
    // Private fields.
    private final BattlefieldGameInstance _game;
    private final IServerPlayer _serverPlayer;
    private final GameTeam _team;


    // Constructors.
    public BattlefieldPlayer(BattlefieldGameInstance game, IServerPlayer player, GameTeam team)
    {
        if (game == null)
        {
            throw new IllegalArgumentException("game is null.");
        }
        if (player == null)
        {
            throw new IllegalArgumentException("game is null.");
        }
        if (team == null)
        {
            throw new IllegalArgumentException("team is null.");
        }

        _game = game;
        _serverPlayer = player;
        _team = team;
    }


    // Inherited methods.
    @Override
    public GameTeam GetTeam()
    {
        return _team;
    }

    @Override
    public IGame GetGame()
    {
        return _game;
    }

    @Override
    public IServerPlayer GetServerPlayer()
    {
        return _serverPlayer;
    }

    @Override
    public Player GetPlayer()
    {
        return _serverPlayer.GetPlayer();
    }
}
