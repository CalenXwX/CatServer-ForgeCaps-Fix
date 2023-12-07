package com.lrw.catserverforgecapsfix.mixin;

import catserver.server.inventory.CatForgeItemCap;
import net.minecraft.nbt.CompoundTag;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CatForgeItemCap.class)
public abstract class MixinCatForgeItemCap
{
    // 修复获取ForgeCaps时访问未初始化属性的问题
    @Inject(
            method = "setItemCap(Lnet/minecraft/world/item/ItemStack;Lorg/bukkit/inventory/ItemStack;)V",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private static void setItemCapInject(net.minecraft.world.item.ItemStack nmsItemStack, ItemStack bukkitItemStack, CallbackInfo callbackInfo)
    {
        if (nmsItemStack != null)
        {
            CompoundTag capNBT = nmsItemStack.serializeCaps();
            if (capNBT != null && !capNBT.isEmpty())
            {
                bukkitItemStack.setForgeItemCap(new CatForgeItemCap(capNBT));
            }
        }
        callbackInfo.cancel();
    }
}
