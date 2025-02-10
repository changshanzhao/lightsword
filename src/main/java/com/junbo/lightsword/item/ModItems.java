package com.junbo.lightsword.item;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.lang.reflect.Constructor;


public class ModItems {
    private ModItems() {
    }

    @SafeVarargs
    private static Item registerToolItem(Class toolClass, ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings, RegistryKey<ItemGroup>... itemGroups) {
        Identifier identifier = settings.getModelId();
        Item customItem;
        try {
            Constructor constructor = Objects.requireNonNull(toolClass).getConstructor(ToolMaterial.class,float.class,float.class,Item.Settings.class);
            customItem = (Item) constructor.newInstance(material, attackDamage, attackSpeed, settings);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Item registeredItem = Registry.register(Registries.ITEM, identifier, customItem);
        for (RegistryKey<ItemGroup> itemGroup : itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(fabricItemGroupEntries -> fabricItemGroupEntries.add(registeredItem));
        }
        return registeredItem;
    }
    public static Item.Settings createDefaultItemSettings(String id) {
        Identifier identifier = Identifier.of("lightsword", id);
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM,identifier));
    }
    public static final Item LIGHT_SWORD = registerToolItem(SwordItem.class, ModMaterials.ModToolMaterials.HEAVEN_TOOL_MATERIAL, 26.0f, -2.0f, createDefaultItemSettings("light_sword"), ItemGroups.COMBAT);


    public static void initialize() {



    }
}


