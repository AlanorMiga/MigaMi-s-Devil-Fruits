package ttv.migami.mdf.client;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.fml.common.Mod;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.client.handler.MovesetHandler;
import ttv.migami.mdf.init.ModItems;
import ttv.migami.mdf.item.FruitItem;

import java.lang.reflect.Field;

/**
 * Author: MrCrayfish
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT)
public class ClientHandler {
    private static Field mouseOptionsField;

    public static void setup() {
        MinecraftForge.EVENT_BUS.register(MovesetHandler.get());
    }

    public static void onRegisterCreativeTab(CreativeModeTabEvent.Register event)
    {
        event.registerCreativeModeTab(new ResourceLocation(Reference.MOD_ID, "creative_tab"), builder ->
        {
            builder.title(Component.translatable("itemGroup." + Reference.MOD_ID));
            builder.icon(() -> {
                ItemStack stack = new ItemStack(ModItems.FIREWORK_FRUIT.get());
                return stack;
            });
            builder.displayItems((flags, output) ->
            {
                ModItems.REGISTER.getEntries().forEach(registryObject ->
                {
                    if(registryObject.get() instanceof FruitItem item)
                    {
                        ItemStack stack = new ItemStack(item);
                        output.accept(stack);
                        return;
                    }
                    output.accept(registryObject.get());
                });
            });
        });
    }

}
