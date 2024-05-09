package sus.keiger.eventplugin.battlefield;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerKit
{
    // Private fields.
    private final HashMap<Integer, ItemStack> _items = new HashMap<>();
    private final Component _name;
    private final Component _description;


    // Constructors.
    public PlayerKit(Component name, Component description)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name is null");
        }
        if (description == null)
        {
            throw new IllegalArgumentException("description is null");
        }

        _name = name;
        _description = description;
    }


    // Methods.
    public List<ItemStack> GetItems()
    {
        return new ArrayList<>(_items.values());
    }

    public List<Integer> GetSlots()
    {
        return new ArrayList<>(_items.keySet());
    }

    public ItemStack GetItem(int slot)
    {
        return _items.get(slot);
    }

    public void SetItem(int slot, ItemStack item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException("item is null");
        }
        _items.put(slot, item);
    }

    public void RemoveItem(int slot)
    {
        _items.remove(slot);
    }

    public void ClearItems()
    {
        _items.clear();
    }

    public void Apply(Player player)
    {
        player.getInventory().clear();

        for (int Slot : _items.keySet())
        {
            player.getInventory().setItem(Slot, _items.get(Slot));
        }
    }
}