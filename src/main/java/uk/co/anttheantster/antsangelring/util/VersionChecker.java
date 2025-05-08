package uk.co.anttheantster.antsangelring.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import uk.co.anttheantster.antsangelring.AntsAngelRing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VersionChecker {

    private static final String VERSION_URL = "https://raw.githubusercontent.com/AntTheAntster/AntsAngelRing/refs/heads/main/version.json";
    private static boolean checked = false;

    private static String latestVersion;
    private static String updateUrl;
    private static String mainChanges;
    private static String messageFromAnt;

    @SubscribeEvent
    public static void clientTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof Player)) return;

        if (!checked && Minecraft.getInstance().player != null) {
            checked = true;
            Player player = ((Player) event.getEntity());
            new Thread(() -> {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(VERSION_URL).openConnection();
                    connection.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    JsonObject json = new JsonParser().parse(response.toString()).getAsJsonObject();
                    latestVersion = json.get("latest_version").getAsString();
                    updateUrl = json.get("update_url").getAsString();
                    mainChanges = json.get("main_changes").getAsString();
                    if (json.get("message_from_ant").getAsString() != null) {
                        messageFromAnt = json.get("message_from_ant").getAsString();
                    }

                    if (!latestVersion.equals(AntsAngelRing.version)){
                        displayMessage(player);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void displayMessage(Player player){
        player.displayClientMessage(
                Component.literal("§cAnt's Angel Ring is outdated! You're using " + AntsAngelRing.version + "! Please update to " + latestVersion + "!"),
                false);
        player.displayClientMessage(
                Component.literal("§c→ " + updateUrl), false);
        player.displayClientMessage(Component.literal("§eMain Changes: " + mainChanges), false);
        if (messageFromAnt != null) {
            player.displayClientMessage(Component.literal("§aMessage From Ant: " + messageFromAnt), false);
        }
    }
}
