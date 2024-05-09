package sus.keiger.eventplugin;

import org.bukkit.entity.Player;

public interface IServerPlayer extends ITickable
{
    Player GetPlayer();

    void OnJoinGameEvent();

    void OnExitGameEvent();

    boolean GetIsHost();

    void SetIsHost(boolean value);
}