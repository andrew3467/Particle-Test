package net.iirc.particletest.visualeffects;


import net.minecraft.client.*;
import net.minecraft.client.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.event.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;
import team.lodestar.lodestone.registry.common.particle.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.helpers.*;

import java.awt.Color;
import java.util.function.Supplier;

import static java.awt.Color.blue;
import static net.minecraft.world.item.Items.STICK;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class particle {
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        final LocalPlayer player = Minecraft.getInstance().player;

        if(player == null){
            return;
        }


        if(player.getItemInHand(player.getUsedItemHand()).getItem() == STICK) {
            if(Minecraft.getInstance().options.keyUse.isDown()) {
                spawnParticles(player.level(), player.getEyePosition());
                double x = player.getLookAngle().x;
                double y = player.getLookAngle().y;
                double z = player.getLookAngle().z;

            }
//
        }
    }

    private static void spawnParticles(Level level, Vec3 pos) {
        Color startingColor = new Color(157, 0, 255);
        Color endingColor = new Color(66, 0, 255);
        WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BACK_OUT).build())
                .setTransparencyData(GenericParticleData.create(0.5F, (float) 0.0F, 0.0F).build())
                .createCircle(level, pos.x, pos.y, pos.z, 2, 5, 1);


    }
}
