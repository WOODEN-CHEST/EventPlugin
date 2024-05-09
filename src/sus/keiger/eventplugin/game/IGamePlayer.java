package sus.keiger.eventplugin.game;

import org.bukkit.entity.Player;
import sus.keiger.eventplugin.IServerPlayer;

public interface IGamePlayer
{
    GameTeam GetTeam();

    IGame GetGame();

    IServerPlayer GetServerPlayer();

    Player GetPlayer();
}