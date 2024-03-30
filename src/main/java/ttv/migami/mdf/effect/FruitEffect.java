package ttv.migami.mdf.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

/**
 * Author: MigaMi
 */
public class FruitEffect extends MobEffect
{
    public float z_damage;
    public boolean z_canBeHeld;
    public int z_cooldown;
    public int z_attackInterval;
    public int z_attackAmount;

    public float x_damage;
    public boolean x_canBeHeld;
    public int x_cooldown;
    public int x_attackInterval;
    public int x_attackAmount;

    public float c_damage;
    public boolean c_canBeHeld;
    public int c_cooldown;
    public int c_attackInterval;
    public int c_attackAmount;

    public float v_damage;
    public boolean v_canBeHeld;
    public int v_cooldown;
    public int v_attackInterval;
    public int v_attackAmount;

    public float f_damage;
    public boolean f_canBeHeld;
    public int f_cooldown;
    public int f_attackInterval;
    public int f_attackAmount;

    public FruitEffect(MobEffectCategory typeIn, int liquidColorIn)
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
