package sus.keiger.eventplugin.game;

import sus.keiger.eventplugin.IServerPlayer;
import sus.keiger.eventplugin.ITickable;

import java.util.List;

public interface IGame extends ITickable
{
    List<IServerPlayer> GetAllPlayers();

    int GetTotalPlayerCount();

    List<IServerPlayer> GetActivePlayers();

    int GetActivePlayerCount();

    GameTeam GetTeam(IServerPlayer player);

    List<GameTeam> GetTeams();
}