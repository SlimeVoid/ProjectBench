package slimevoid.projectbench.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import slimevoid.projectbench.blocks.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBase extends ItemBlock {
	
	protected HashMap itemBlockNames;
	protected ArrayList validItemBlocks;

	public ItemBase(int itemId) {
		super(itemId);
		itemBlockNames = new HashMap();
		validItemBlocks = new ArrayList();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	public void setMetaName(int damage, String name) {
		itemBlockNames.put(Integer.valueOf(damage), name);
		validItemBlocks.add(Integer.valueOf(damage));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String itemName = (String) itemBlockNames.get(Integer.valueOf(itemstack.getItemDamage()));
		if (itemName == null) {
			throw new IndexOutOfBoundsException();
		} else {
			return itemName;
		}
	}
	
/*	@Override
	public void getSubItems(int itemId, CreativeTabs creativeTabs, List creativeList) {
		Integer i;
		for (Iterator item = validItemBlocks.iterator(); item.hasNext(); creativeList.add(new ItemStack(this, 1, i.intValue()))) {
			i = (Integer) item.next();
		}
	}*/
	
	@Override
	public boolean placeBlockAt(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		if (!world.setBlock(x, y, z, this.itemID, metadata, 0x3)) {
			return false;
		}
		if (world.getBlockId(x, y, z) == this.itemID) {
			BlockBase blockBase = (BlockBase) Block.blocksList[this.itemID];
			if (blockBase != null) {
				blockBase.onBlockPlaced(world, x, y, z, side, entityplayer, itemstack);
			}
		}
		return true;
	}
}
