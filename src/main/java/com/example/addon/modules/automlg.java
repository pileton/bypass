package your.addon;

import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoMLG extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public AutoMLG() {
        super(BypassUtilsAddon.CATEGORY, "auto-mlg", "Automatically places water when falling 5+ blocks.");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (mc.player == null || mc.world == null) return;

        if (mc.player.fallDistance > 5 && mc.player.getVelocity().y < -0.5) {
            int bucketSlot = findWaterBucketSlot();
            if (bucketSlot != -1) {
                int prevSlot = mc.player.getInventory().selectedSlot;
                mc.player.getInventory().selectedSlot = bucketSlot;

                BlockPos pos = mc.player.getBlockPos().down();
                mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND,
                    new BlockHitResult(Vec3d.ofCenter(pos), Direction.UP, pos, false));

                mc.player.getInventory().selectedSlot = prevSlot;
            }
        }
    }

    private int findWaterBucketSlot() {
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.WATER_BUCKET) {
                return i;
            }
        }
        return -1;
    }
}
