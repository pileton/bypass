package your.addon;

import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.systems.modules.Category;

public class BypassUtilsAddon extends MeteorAddon {
    public static final Category CATEGORY = new Category("Bypass & Utilities");

    @Override
    public void onInitialize() {
        System.out.println("Bypass & Utilities Addon Loaded!");
        Modules.get().add(new AntiChatMute());
        Modules.get().add(new AutoMLG());
    }
}
