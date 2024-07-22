package ewewukek.nighteyemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class NightEyeMod implements ModInitializer {
    @Override
    public void onInitialize() {
        PayloadTypeRegistry.playS2C().register(AllowPacket.ID, AllowPacket.CODEC);
        ServerPlayConnectionEvents.JOIN.register((netHandler, packetSender, server) -> {
            netHandler.sendPacket(ServerPlayNetworking.createS2CPacket(AllowPacket.INSTANCE));
        });
    }
}
