package ttv.migami.mdf.effect.fruit;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;
import ttv.migami.mdf.effect.FruitEffect;

import java.util.Collections;
import java.util.List;

/**
 * Author: MigaMi
 */
public class SkeletonFruitEffect extends FruitEffect
{
    public float z_damage = 5.0F;
    public boolean z_canBeHeld = true;
    public int z_cooldown = 160;
    public int z_attackInterval = 5;
    public int z_attackAmount = 5;

    public float x_damage = 5.0F;
    public boolean x_canBeHeld = false;
    public int x_cooldown = 160;
    public int x_attackInterval = 0;
    public int x_attackAmount = 2;

    public float c_damage = 5.0F;
    public boolean c_canBeHeld = false;
    public int c_cooldown = 100;
    public int c_attackInterval = 0;
    public int c_attackAmount = 1;

    public float v_damage = 5.0F;
    public boolean v_canBeHeld = false;
    public int v_cooldown = 300;
    public int v_attackInterval = 0;
    public int v_attackAmount = 1;

    public float f_damage = 0.0F;
    public boolean f_canBeHeld = false;
    public int f_cooldown = 100;
    public int f_attackInterval = 0;
    public int f_attackAmount = 1;

    public SkeletonFruitEffect(MobEffectCategory typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public List<ItemStack> getCurativeItems()
    {
        return Collections.emptyList();
    }

    public float getZDamage() { return z_damage; }
    public boolean getZCanBeHeld() { return z_canBeHeld; }
    public int getZCooldown() { return z_cooldown; }
    public int getZAttackInterval() { return z_attackInterval; }
    public int getZAttackAmount() { return z_attackAmount; }

    public float getXDamage() { return x_damage; }
    public boolean getXCanBeHeld() { return x_canBeHeld; }
    public int getXCooldown() { return x_cooldown; }
    public int getXAttackInterval() { return x_attackInterval; }
    public int getXAttackAmount() { return x_attackAmount; }

    public float getCDamage() { return c_damage; }
    public boolean getCCanBeHeld() { return c_canBeHeld; }
    public int getCCooldown() { return c_cooldown; }
    public int getCAttackInterval() { return c_attackInterval; }
    public int getCAttackAmount() { return c_attackAmount; }

    public float getVDamage() { return v_damage; }
    public boolean getVCanBeHeld() { return v_canBeHeld; }
    public int getVCooldown() { return v_cooldown; }
    public int getVAttackInterval() { return v_attackInterval; }
    public int getVAttackAmount() { return v_attackAmount; }

    public float getFDamage() { return f_damage; }
    public boolean getFCanBeHeld() { return f_canBeHeld; }
    public int getFCooldown() { return f_cooldown; }
    public int getFAttackInterval() { return f_attackInterval; }
    public int getFAttackAmount() { return f_attackAmount; }
}
