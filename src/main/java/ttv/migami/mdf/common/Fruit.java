package ttv.migami.mdf.common;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;
import ttv.migami.mdf.annotation.Optional;

import java.util.List;

public class Fruit implements INBTSerializable<CompoundTag>
{
    protected ZMove zMove = new ZMove();

    public ZMove getZMove() { return this.zMove; }

    public static class ZMove implements INBTSerializable<CompoundTag>
    {
        private float damage;
        @Optional
        private boolean canBeHeld;
        @Optional
        private int cooldown;
        @Optional
        private int attackInterval;
        @Optional
        private int attackAmount;

        @Override
        public CompoundTag serializeNBT()
        {
            CompoundTag tag = new CompoundTag();
            tag.putFloat("Damage", this.damage);
            tag.putBoolean("CanBeHeld", this.canBeHeld);
            tag.putInt("Cooldown", this.cooldown);
            tag.putInt("AttackInterval", this.attackInterval);
            tag.putInt("AttackAmount", this.attackAmount);
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag tag)
        {
            if(tag.contains("Damage", Tag.TAG_ANY_NUMERIC))
            {
                this.damage = tag.getFloat("Damage");
            }
            if(tag.contains("CanBeHeld", Tag.TAG_ANY_NUMERIC))
            {
                this.canBeHeld = tag.getBoolean("CanBeHeld");
            }
            if(tag.contains("Cooldown", Tag.TAG_ANY_NUMERIC))
            {
                this.cooldown = tag.getInt("Cooldown");
            }
            if(tag.contains("AttackInterval", Tag.TAG_ANY_NUMERIC))
            {
                this.attackInterval = tag.getInt("AttackInterval");
            }
            if(tag.contains("AttackAmount", Tag.TAG_ANY_NUMERIC))
            {
                this.attackAmount = tag.getInt("AttackAmount");
            }
        }

        public JsonObject toJsonObject()
        {
            Preconditions.checkArgument(this.damage > 0, "Damage must be more than zero");
            JsonObject object = new JsonObject();
            object.addProperty("damage", this.damage);
            if(this.canBeHeld) object.addProperty("canBeHeld", true);
            if(this.cooldown != 0) object.addProperty("cooldown", this.cooldown);
            if(this.attackInterval != 0) object.addProperty("attackInterval", this.attackInterval);
            if(this.attackAmount != 0) object.addProperty("attackAmount", this.attackAmount);
            return object;
        }

        /**
         * @return A copy of the zMove get
         */
        public ZMove copy()
        {
            ZMove zMove = new ZMove();
            zMove.damage = this.damage;
            zMove.canBeHeld = this.canBeHeld;
            zMove.cooldown = this.cooldown;
            zMove.attackInterval = this.attackInterval;
            zMove.attackAmount = this.attackAmount;
            return zMove;
        }

        /**
         * @return The fruit's damage
         */
        public float getDamage()
        {
            return this.damage;
        }

        /**
         * @return The fruit's damage
         */
        public boolean getCanBeHeld()
        {
            return this.canBeHeld;
        }

        /**
         * @return The fruit's cooldown
         */
        public int getCooldown()
        {
            return this.cooldown;
        }

        /**
         * @return The fruit's attack interval
         */
        public int getAttackInterval()
        {
            return this.attackInterval;
        }

        /**
         * @return The fruit's attack amount
         */
        public int getAttackAmount()
        {
            return this.attackAmount;
        }

    }

    @Override
    public CompoundTag serializeNBT()
    {
        CompoundTag tag = new CompoundTag();
        tag.put("ZMove", this.zMove.serializeNBT());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag)
    {
        if(tag.contains("ZMove", Tag.TAG_COMPOUND))
        {
            this.zMove.deserializeNBT(tag.getCompound("ZMove"));
        }
    }

    public JsonObject toJsonObject()
    {
        JsonObject object = new JsonObject();
        object.add("ZMove", this.zMove.toJsonObject());
        return object;
    }

    public static Fruit create(CompoundTag tag)
    {
        Fruit fruit = new Fruit();
        fruit.deserializeNBT(tag);
        return fruit;
    }

    public Fruit copy()
    {
        Fruit fruit = new Fruit();
        fruit.zMove = this.zMove.copy();
        return fruit;
    }

    public static class Builder
    {
        private final Fruit fruit;

        private Builder()
        {
            this.fruit = new Fruit();
        }

        public static Builder create()
        {
            return new Builder();
        }

        public Fruit build()
        {
            return this.fruit.copy(); //Copy since the builder could be used again
        }

        public Builder setDamage(float damage)
        {
            this.fruit.zMove.damage = damage;
            return this;
        }

        public Builder canBeHeld(boolean canBeHeld)
        {
            this.fruit.zMove.canBeHeld = canBeHeld;
            return this;
        }

        public Builder setCooldown(int cooldown)
        {
            this.fruit.zMove.cooldown = cooldown;
            return this;
        }

        public Builder setAttackInterval(int attackInterval)
        {
            this.fruit.zMove.attackInterval = attackInterval;
            return this;
        }

        public Builder setAttackAmount(int attackAmount)
        {
            this.fruit.zMove.attackAmount = attackAmount;
            return this;
        }

    }
}
