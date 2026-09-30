package com.enchantedduplication.registry;

import com.enchantedduplication.EnchantedDuplication;
import com.enchantedduplication.menu.RewritingTableMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, EnchantedDuplication.MOD_ID);
    public static final DeferredHolder<MenuType<?>, MenuType<RewritingTableMenu>> REWRITING_TABLE = MENUS.register("rewriting_table", () -> IMenuTypeExtension.create(RewritingTableMenu::new));
    private ModMenus() {}
}
