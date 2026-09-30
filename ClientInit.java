package com.enchantedduplication;

import com.enchantedduplication.client.RewritingTableScreen;
import com.enchantedduplication.registry.ModMenus;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = EnchantedDuplication.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public final class ClientInit {
    private ClientInit() {}
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.REWRITING_TABLE.get(), RewritingTableScreen::new);
    }
}
