package ttv.migami.mdf.client.handler;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import ttv.migami.mdf.Reference;
import ttv.migami.mdf.client.KeyBinds;
import ttv.migami.mdf.effect.FruitEffect;
import ttv.migami.mdf.init.ModEffects;
import ttv.migami.mdf.network.PacketHandler;
import ttv.migami.mdf.network.message.C2SFruitMessage;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT)
public class MovesetHandler {

    private static MovesetHandler instance;

    FruitEffect effect;
    int fruit;

    private static int z_cooldown;
    private static int z_interval;
    private static int z_amount;
    private static int x_cooldown = 0;
    private static int x_interval = 0;
    private static int x_amount = 0;
    private static int c_cooldown = 0;
    private static int c_interval = 0;
    private static int c_amount = 0;
    private static int v_cooldown = 0;
    private static int v_interval = 0;
    private static int v_amount = 0;
    private static int f_cooldown = 0;
    private static int f_interval = 0;
    private static int f_amount = 0;

    public static MovesetHandler get()
    {
        if(instance == null)
        {
            instance = new MovesetHandler();
        }
        return instance;
    }

    private MovesetHandler()
    {
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        Player player = Minecraft.getInstance().player;
        if(player == null)
            return;

        if (player.hasEffect(ModEffects.FIREWORK_FRUIT.get())) {
            effect = ModEffects.FIREWORK_FRUIT.get();
            fruit = 1;
        }
        else if (player.hasEffect(ModEffects.CREEPER_FRUIT.get())) {
            effect = ModEffects.CREEPER_FRUIT.get();
            fruit = 2;
        }
        else if (player.hasEffect(ModEffects.SKELETON_FRUIT.get())) {
            effect = ModEffects.SKELETON_FRUIT.get();
            fruit = 3;
        }
        else {
            effect = null;
        }

        if (effect != null)
        {
            if (z_cooldown > 0) {
                z_cooldown--;
            }
            if (z_interval > 0) {
                z_interval--;
            }
            if (z_cooldown == 0 && z_amount == 0) {
                z_amount = effect.getZAttackAmount();
                Minecraft.getInstance().player.sendSystemMessage(Component.translatable("info.mdf.z_ready", KeyBinds.KEY_Z_ACTION.getTranslatedKeyMessage().getString().toUpperCase(Locale.ENGLISH)).withStyle(ChatFormatting.YELLOW));
            }
            if (x_cooldown > 0) {
                x_cooldown--;
            }
            if (x_interval > 0) {
                x_interval--;
            }
            if (x_cooldown == 0 && x_amount == 0) {
                x_amount = effect.getXAttackAmount();
                Minecraft.getInstance().player.sendSystemMessage(Component.translatable("info.mdf.x_ready", KeyBinds.KEY_X_ACTION.getTranslatedKeyMessage().getString().toUpperCase(Locale.ENGLISH)).withStyle(ChatFormatting.YELLOW));
            }
            if (c_cooldown > 0) {
                c_cooldown--;
            }
            if (c_interval > 0) {
                c_interval--;
            }
            if (c_cooldown == 0 && c_amount == 0) {
                c_amount = effect.getCAttackAmount();
                Minecraft.getInstance().player.sendSystemMessage(Component.translatable("info.mdf.c_ready", KeyBinds.KEY_C_ACTION.getTranslatedKeyMessage().getString().toUpperCase(Locale.ENGLISH)).withStyle(ChatFormatting.YELLOW));
            }
            if (v_cooldown > 0) {
                v_cooldown--;
            }
            if (v_interval > 0) {
                v_interval--;
            }
            if (v_cooldown == 0 && v_amount == 0) {
                v_amount = effect.getVAttackAmount();
                Minecraft.getInstance().player.sendSystemMessage(Component.translatable("info.mdf.v_ready", KeyBinds.KEY_V_ACTION.getTranslatedKeyMessage().getString().toUpperCase(Locale.ENGLISH)).withStyle(ChatFormatting.YELLOW));
            }
            if (f_cooldown > 0) {
                f_cooldown--;
            }
            if (f_interval > 0) {
                f_interval--;
            }
            if (f_cooldown == 0 && f_amount == 0) {
                f_amount = effect.getFAttackAmount();
                Minecraft.getInstance().player.sendSystemMessage(Component.translatable("info.mdf.f_ready", KeyBinds.KEY_F_ACTION.getTranslatedKeyMessage().getString().toUpperCase(Locale.ENGLISH)).withStyle(ChatFormatting.YELLOW));
            }
        }
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.Key event)
    {
        Player player = Minecraft.getInstance().player;
        if(player == null)
            return;

        ItemStack stack = player.getMainHandItem();
        ItemStack stack2 = player.getOffhandItem();

        if (effect != null)
        {
            if (stack.isEmpty() && stack2.isEmpty()) {
                if (KeyBinds.KEY_Z_ACTION.isDown() && z_cooldown == 0) {
                    if (effect.getZCanBeHeld() || event.getAction() == GLFW.GLFW_PRESS) {
                        if (z_interval == 0 && z_amount > 0) {
                            //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Used Z action!"));
                            PacketHandler.getPlayChannel().sendToServer(new C2SFruitMessage(fruit, 1));
                            z_interval = effect.getZAttackInterval();
                            z_amount--;
                            //player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 20, 0, false, false));
                        }
                    }
                    if (z_amount == 0) {
                        z_cooldown = effect.getZCooldown();
                    }
                }
                if (KeyBinds.KEY_X_ACTION.isDown() && x_cooldown == 0) {
                    if (effect.getXCanBeHeld() || event.getAction() == GLFW.GLFW_PRESS) {
                        if (x_interval == 0 && x_amount > 0) {
                            //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Used X action!"));
                            PacketHandler.getPlayChannel().sendToServer(new C2SFruitMessage(fruit, 2));
                            x_interval = effect.getXAttackInterval();
                            x_amount--;
                        }
                    }
                    if (x_amount == 0) {
                        x_cooldown = effect.getXCooldown();
                    }
                }
                if (KeyBinds.KEY_C_ACTION.isDown() && c_cooldown == 0) {
                    if (effect.getCCanBeHeld() || event.getAction() == GLFW.GLFW_PRESS) {
                        if (c_interval == 0 && c_amount > 0) {
                            //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Used C action!"));
                            PacketHandler.getPlayChannel().sendToServer(new C2SFruitMessage(fruit, 3));
                            c_interval = effect.getCAttackInterval();
                            c_amount--;
                        }
                    }
                    if (c_amount == 0) {
                        c_cooldown = effect.getCCooldown();
                    }
                }
                if (KeyBinds.KEY_V_ACTION.isDown() && v_cooldown == 0) {
                    if (effect.getVCanBeHeld() || event.getAction() == GLFW.GLFW_PRESS) {
                        if (v_interval == 0 && v_amount > 0) {
                            //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Used V action!"));
                            PacketHandler.getPlayChannel().sendToServer(new C2SFruitMessage(fruit, 4));
                            v_interval = effect.getVAttackInterval();
                            v_amount--;
                        }
                    }
                    if (v_amount == 0) {
                        v_cooldown = effect.getVCooldown();
                    }
                }
                if (KeyBinds.KEY_F_ACTION.isDown() && f_cooldown == 0) {
                    if (effect.getFCanBeHeld() || event.getAction() == GLFW.GLFW_PRESS) {
                        if (f_interval == 0 && f_amount > 0) {
                            //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Used F action!"));
                            PacketHandler.getPlayChannel().sendToServer(new C2SFruitMessage(fruit, 5));
                            f_interval = effect.getFAttackInterval();
                            f_amount--;
                        }
                    }
                    if (f_amount == 0) {
                        f_cooldown = effect.getFCooldown();
                    }
                }
            }
        }
    }
}
