package se.mickelus.tetra.items.journal;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.mickelus.tetra.TetraMod;
import se.mickelus.tetra.items.TetraCreativeTabs;
import se.mickelus.tetra.items.TetraItem;
import se.mickelus.tetra.items.journal.gui.GuiJournal;
import se.mickelus.tetra.items.toolbelt.GuiHandlerToolbelt;

public class ItemJournal extends TetraItem {

    private static final String unlocalizedName = "journal";

    @GameRegistry.ObjectHolder(TetraMod.MOD_ID + ":" + unlocalizedName)
    public static ItemJournal instance;

    public ItemJournal() {
        super();

        setRegistryName(unlocalizedName);
        setUnlocalizedName(unlocalizedName);

        setMaxStackSize(1);

        setCreativeTab(TetraCreativeTabs.getInstance());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote) {
            showGui();
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    private void showGui() {
        Minecraft.getMinecraft().displayGuiScreen(new GuiJournal());
    }
    
}
