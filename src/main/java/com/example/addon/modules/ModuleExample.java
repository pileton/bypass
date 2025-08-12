package your.addon;

import meteordevelopment.meteorclient.events.game.SendChatMessageEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;

public class AntiChatMute extends Module {
    public AntiChatMute() {
        super(BypassUtilsAddon.CATEGORY, "anti-chat-mute", "Sends messages as /me to bypass mutes.");
    }

    @EventHandler
    private void onSendChat(SendChatMessageEvent event) {
        if (!event.message.startsWith("/minecraft:me ")) {
            event.cancel();
            MinecraftClient.getInstance().player.networkHandler.sendChatCommand("minecraft:me " + event.message);
        }
    }
}
