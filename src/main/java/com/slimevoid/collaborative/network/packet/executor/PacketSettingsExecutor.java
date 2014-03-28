package com.slimevoid.collaborative.network.packet.executor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.slimevoid.library.IPacketExecutor;
import net.slimevoid.library.network.PacketUpdate;

import com.slimevoid.collaborative.container.ContainerWorkBench;
import com.slimevoid.collaborative.core.lib.ConfigurationLib;
import com.slimevoid.collaborative.network.packet.PacketSettings;

public class PacketSettingsExecutor implements IPacketExecutor {

    @Override
    public void execute(PacketUpdate packet, World world, EntityPlayer entityplayer) {
        if (packet instanceof PacketSettings) {
            PacketSettings packetSettings = (PacketSettings) packet;
            ConfigurationLib.updatePlayersInventoryLocked(entityplayer,
                                                          packetSettings.getInventoryMode());
            if (entityplayer.openContainer != null
                && entityplayer.openContainer instanceof ContainerWorkBench) {
                ContainerWorkBench bench = ((ContainerWorkBench) entityplayer.openContainer);
                bench.onCraftMatrixChanged(bench.getPlayerInventory());
            }
        }
    }

}
