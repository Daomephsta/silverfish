package daomephsta.silverfish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;

public class Silverfish implements ModInitializer
{
    public static final Logger LOGGER = LoggerFactory.getLogger("Silverfish");

    @Override
    public void onInitialize()
    {
        new Item(new Item.Settings());
    }
}
