package ttv.migami.mdf.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.fml.common.Mod;
import ttv.migami.mdf.Reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class JsonReader implements ResourceManagerReloadListener {

    private static final Gson GSON = new Gson();
    private static JsonObject jsonObject;

    public void onResourceManagerReload(ResourceManager resourceManager) {
        try {
            ResourceLocation location = new ResourceLocation(Reference.MOD_ID, "fruit/firework_fruit.json");
            Optional<Resource> resourceOptional = resourceManager.getResource(location);

            if (resourceOptional.isPresent()) {
                Resource resource = resourceOptional.get();
                BufferedReader reader = new BufferedReader(resource.openAsReader());
                jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                reader.close();
                System.out.println("aaaa you. It's working");
            }
            else {
                System.out.println("aaaa you");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double getDamage() {
        if (jsonObject != null && jsonObject.has("ZMove")) {
            return jsonObject.getAsJsonObject("ZMove").get("damage").getAsDouble();
        }
        return 0.0;
    }

    public static int getCooldown() {
        if (jsonObject != null && jsonObject.has("ZMove")) {
            return jsonObject.getAsJsonObject("ZMove").get("cooldown").getAsInt();
        }
        return 0;
    }
}