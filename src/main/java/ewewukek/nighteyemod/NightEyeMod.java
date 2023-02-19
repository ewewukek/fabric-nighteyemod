package ewewukek.nighteyemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class NightEyeMod implements ModInitializer {
    public static final Identifier ALLOW_PACKET_ID = new Identifier("nighteye", "allow");

    @Override
    public void onInitialize() {
        ServerPlayConnectionEvents.JOIN.register((netHandler, packetSender, server) -> {
            netHandler.sendPacket(ServerPlayNetworking.createS2CPacket(ALLOW_PACKET_ID, PacketByteBufs.create()));
        });
    }
}
