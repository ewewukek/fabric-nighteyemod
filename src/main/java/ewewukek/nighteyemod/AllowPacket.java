package ewewukek.nighteyemod;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class AllowPacket implements CustomPayload {
    public static final AllowPacket INSTANCE = new AllowPacket();
    public static final CustomPayload.Id<AllowPacket> ID =
        new CustomPayload.Id<>(Identifier.of("nighteyemod", "allow"));
    public static final PacketCodec<RegistryByteBuf, AllowPacket> CODEC =
        PacketCodec.unit(INSTANCE);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
