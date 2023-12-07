package com.lrw.catserverforgecapsfix;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

@OnlyIn(Dist.DEDICATED_SERVER)
@Mod(CatServerForgeCapsFix.MOD_ID)
public class CatServerForgeCapsFix
{
    public static final String MOD_ID = "catserverforgecapsfix";

    public static final Logger LOGGER = LogUtils.getLogger();

    public CatServerForgeCapsFix()
    {
        // Mixin CatServer
        try
        {
            Mixins.addConfiguration("mixins.catserverforgecapsfix.json");
            LOGGER.info("Fix CatServer For Slash Blade已加载");
        }
        catch (Exception e)
        {
            LOGGER.error("Fix CatServer For Slash Blade未成功加载", e);
        }
    }
}
