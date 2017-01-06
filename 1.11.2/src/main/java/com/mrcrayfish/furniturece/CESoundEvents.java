package com.mrcrayfish.furniturece;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class CESoundEvents {
	public static final SoundEvent bear = getRegisteredSoundEvent("cfmce:roar");


    private static SoundEvent getRegisteredSoundEvent(String name) {
        return SoundEvent.REGISTRY.getObject(new ResourceLocation(name));
    }
}
