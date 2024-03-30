package ttv.migami.mdf.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.common.Fruit;

import java.util.concurrent.CompletableFuture;

/**
 * Author: MigaMi
 */
public class FruitGen extends FruitProvider
{
    public FruitGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries);
    }

    @Override
    protected void registerFruits()
    {
        /* Scrap Tier */
        this.addFruit(new ResourceLocation(Reference.MOD_ID, "firework_blessing"), Fruit.Builder.create()

                // Z-Move
                .setDamage(5.0F)
                .canBeHeld(false)
                .setCooldown(100)

                .build());

    }
}
