package ttv.migami.mdf;

import com.mrcrayfish.framework.api.client.FrameworkClientAPI;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;
import ttv.migami.mdf.client.ClientHandler;
import ttv.migami.mdf.client.KeyBinds;
import ttv.migami.mdf.client.MetaLoader;
import ttv.migami.mdf.datagen.FruitGen;
import ttv.migami.mdf.datagen.WorldGen;
import ttv.migami.mdf.entity.client.skeleton.BoneGeoRenderer;
import ttv.migami.mdf.entity.client.skeleton.BoneZoneGeoRenderer;
import ttv.migami.mdf.entity.client.skeleton.GasterBlasterGeoRenderer;
import ttv.migami.mdf.init.*;
import ttv.migami.mdf.network.PacketHandler;
import ttv.migami.mdf.world.loot.ModLootModifiers;

import java.util.concurrent.CompletableFuture;

@Mod(Reference.MOD_ID)
public class DevilFruits {
    public static boolean debugging = false;
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

    public DevilFruits() {
        //ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientSpec);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.serverSpec);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        ModEffects.REGISTER.register(bus);
        ModEnchantments.REGISTER.register(bus);
        ModItems.REGISTER.register(bus);
        ModBlocks.REGISTER.register(bus);
        ModEntities.REGISTER.register(bus);
        ModParticleTypes.REGISTER.register(bus);
        ModSounds.REGISTER.register(bus);
        ModLootModifiers.register(bus);
        bus.addListener(this::onCommonSetup);
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onGatherData);

        // OooOoOh spooky!
        GeckoLib.initialize();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            FrameworkClientAPI.registerDataLoader(MetaLoader.getInstance());
            ClientHandler.onRegisterCreativeTab(bus);
            bus.addListener(KeyBinds::registerKeyMappings);
        });

    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ClientHandler::setup);
        EntityRenderers.register(ModEntities.GASTER_BLASTER.get(), GasterBlasterGeoRenderer::new);
        EntityRenderers.register(ModEntities.SMALL_BONE.get(), BoneGeoRenderer::new);
        EntityRenderers.register(ModEntities.BONE_ZONE.get(), BoneZoneGeoRenderer::new);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() ->
        {
            PacketHandler.init();
        });
    }

    private void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new FruitGen(output, lookupProvider));
        generator.addProvider(event.includeServer(), new WorldGen(output, lookupProvider));
    }

    public static boolean isDebugging() {
        return false; //!FMLEnvironment.production;
    }
}
