package daomephsta.silverfish.client;

import java.util.Optional;

import daomephsta.silverfish.Silverfish;
import daomephsta.silverfish.Silverfish.DistributionProxy;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;

public class SilverfishClientInitialiser implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        new Silverfish(new DistributionProxy()
        {
            @Override
            public Optional<DynamicRegistryManager> getRegistryManager()
            {
                return Optional.ofNullable(MinecraftClient.getInstance().world)
                    .map(World::getRegistryManager);
            }
        });
    }
}
