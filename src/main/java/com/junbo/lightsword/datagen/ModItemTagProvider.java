package com.junbo.lightsword.datagen;

import com.junbo.lightsword.item.ModItems;
import com.junbo.lightsword.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        //配置可以修复heaven_tool的物品
        getOrCreateTagBuilder(ModTags.Items.REPAIRS_HEAVEN_ARMOR)
                .add(net.minecraft.item.Items.GLASS);

        //让工具限定附魔 需要将工具添加进入对应的Tag
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.LIGHT_SWORD);

    }
}
