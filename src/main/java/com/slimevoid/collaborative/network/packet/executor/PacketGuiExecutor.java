package com.slimevoid.collaborative.network.packet.executor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slimevoid.library.IPacketExecutor;
import net.slimevoid.library.network.PacketUpdate;

import com.slimevoid.collaborative.container.ContainerWorkBench;
import com.slimevoid.collaborative.network.packet.PacketGui;
import com.slimevoid.collaborative.tileentity.TileEntityWorkBench;

public class PacketGuiExecutor implements IPacketExecutor {

    @Override
    public void execute(PacketUpdate packet, World world, EntityPlayer entityplayer) {
        if (packet instanceof PacketGui) {
            PacketGui packetGui = (PacketGui) packet;
            if (packet.targetExists(world)) {
                TileEntity tileentity = packetGui.getTarget(world);
                if (tileentity != null
                    && tileentity instanceof TileEntityWorkBench) {
                    if (entityplayer.openContainer instanceof ContainerWorkBench) {
                        ((ContainerWorkBench) entityplayer.openContainer).handleGuiEvent(packetGui);
                    }
                }
            }
        }
    }

}
