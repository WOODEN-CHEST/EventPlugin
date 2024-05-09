package sus.keiger.eventplugin.game;

import net.kyori.adventure.text.format.TextColor;
import sus.keiger.eventplugin.IServerPlayer;

import java.util.List;

public class GameTeam
{
    // Private fields.
    private final IGame _game;
    private final TextColor _color;
    private final String _name;


    // Constructors.
    public GameTeam(IGame game, String name, TextColor color)
    {
        if (game == null)
        {
            throw new IllegalArgumentException();
        }
        if (name == null)
        {
            throw new IllegalArgumentException();
        }
        if (name.isBlank())
        {
            throw new IllegalArgumentException("Name is blank");
        }
        if (color == null)
        {
            throw new IllegalArgumentException();
        }

        _game = game;
        _color = color;
        _name = name;
    }



    // Methods.
    public List<IServerPlayer> GetActivePlayers()
    {
        return _game.GetActivePlayers().stream().filter(player -> _game.GetTeam(player) == this).toList();
    }

    public List<IServerPlayer> GetAllPlayers()
    {
        return _game.GetAllPlayers().stream().filter(player -> _game.GetTeam(player) == this).toList();
    }

    public TextColor GetColor()
    {
        return _color;
    }

    public String GetName()
    {
        return _name;
    }
}