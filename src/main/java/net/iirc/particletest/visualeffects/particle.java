package net.iirc.particletest.visualeffects;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.iirc.particletest.ParticleTest;
import net.minecraft.client.*;
import net.minecraft.client.player.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.*;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.phys.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.registry.common.particle.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.awt.Color;
import java.util.function.Supplier;

import static java.awt.Color.blue;
import static java.awt.Color.green;
import static net.minecraft.world.item.Items.STICK;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class particle {
    public static final ResourceLocation UV_GRID = new ResourceLocation(ParticleTest.MODID, "textures/vfx/uv_test.png");


    @SubscribeEvent
    public static void renderLevelStageEvent(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_BLOCK_ENTITIES) return;
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player != null) {
            Camera camera = event.getCamera();
            PoseStack poseStack = event.getPoseStack();
            Vec3 renderPos = new Vec3(0, 25, 0);
            Vec3 cameraPos = camera.getPosition();
            Vec3 relativePos = renderPos.subtract(cameraPos);

            float radius = 3.0f;

            poseStack.pushPose();
            poseStack.translate(relativePos.x, relativePos.y, relativePos.z);

            VFXBuilders.WorldVFXBuilder builder = new VFXBuilders.WorldVFXBuilder();

            VertexConsumer vertexConsumer =
                    RenderHandler.DELAYED_RENDER.getBuffer(LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE_TRIANGLE.applyAndCache(UV_GRID));

            builder.renderSphere(vertexConsumer, poseStack, radius, 20, 20);
            poseStack.popPose();
        }
    }
}