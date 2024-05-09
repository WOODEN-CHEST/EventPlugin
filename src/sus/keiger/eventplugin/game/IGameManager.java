package sus.keiger.eventplugin.game;

public interface IGameManager
{
    void OnGameStart(IGame game);

    void OnGameComplete(IGame game);
}