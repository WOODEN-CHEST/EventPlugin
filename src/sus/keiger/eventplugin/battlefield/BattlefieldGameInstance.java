package sus.keiger.eventplugin.battlefield;

import sus.keiger.eventplugin.IServerPlayer;
import sus.keiger.eventplugin.game.GameTeam;
import sus.keiger.eventplugin.game.IGame;
import sus.keiger.eventplugin.game.IGamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class BattlefieldGameInstance implements IGame
{

    // Private fields.
    private final HashMap<IServerPlayer, BattlefieldPlayer> _players = new HashMap<>();
    private final HashSet<IServerPlayer> _activePlayers = new HashSet<>();


    // Constructors.
    public BattlefieldGameInstance()
    {

    }


    // Methods.



    // Inherited methods.
    @Override
    public void Tick()
    {

    }


    // Inherited methods.
    @Override
    public List<IServerPlayer> GetAllPlayers()
    {
        return new ArrayList<>(_players.keySet());
    }

    @Override
    public int GetTotalPlayerCount()
    {
        return _players.size();
    }

    @Override
    public List<IServerPlayer> GetActivePlayers()
    {
        return new ArrayList<>(_activePlayers);
    }

    @Override
    public int GetActivePlayerCount()
    {
        return _activePlayers.size();
    }

    @Override
    public GameTeam GetTeam(IServerPlayer player)
    {
        if (player == null)
        {
            throw new IllegalArgumentException("player is null");
        }
        IGamePlayer GamePlayer = _players.get(player);
        return GamePlayer != null ? GamePlayer.GetTeam() : null;
    }

    @Override
    public List<GameTeam> GetTeams()
    {
        return _players.values().stream().map(IGamePlayer::GetTeam).distinct().toList();
    }
}