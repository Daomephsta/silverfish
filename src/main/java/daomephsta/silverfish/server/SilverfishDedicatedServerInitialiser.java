package daomephsta.silverfish.server;

import java.util.Optional;

import daomephsta.silverfish.Silverfish;
import daomephsta.silverfish.Silverfish.DistributionProxy;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.registry.DynamicRegistryManager;

public class SilverfishDedicatedServerInitialiser implements DedicatedServerModInitializer
{
    private MinecraftServer server = null;

    @Override
    public void onInitializeServer()
    {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> this.server = server);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> this.server = null);
        new Silverfish(new DistributionProxy()
        {
            @Override
            public Optional<DynamicRegistryManager> getRegistryManager()
            {
                return Optional.ofNullable(server)
                    .map(server -> server.getOverworld().getRegistryManager());
            }
        });
    }
}
