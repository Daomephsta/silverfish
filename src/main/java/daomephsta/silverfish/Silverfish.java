package daomephsta.silverfish;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.util.registry.DynamicRegistryManager;

public class Silverfish
{
    public static final Logger LOGGER = LoggerFactory.getLogger("Silverfish");
    private final DistributionProxy proxy;
    private static Silverfish INSTANCE;

    public Silverfish(DistributionProxy proxy)
    {
        this.proxy = proxy;
        INSTANCE = this;
    }

    public static Optional<DistributionProxy> proxy()
    {
        return Optional.ofNullable(INSTANCE).map(silverfish -> silverfish.proxy);
    }

    public interface DistributionProxy
    {
        public Optional<DynamicRegistryManager> getRegistryManager();
    }
}
