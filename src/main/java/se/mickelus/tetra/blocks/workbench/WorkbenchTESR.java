package se.mickelus.tetra.blocks.workbench;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WorkbenchTESR extends TileEntityRenderer<WorkbenchTile> {

    private ItemRenderer itemRenderer;

    public WorkbenchTESR(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
        itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(WorkbenchTile workbenchTile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        ItemStack itemStack = workbenchTile.getTargetItemStack();
        if (itemStack != null && !itemStack.isEmpty()) {
            matrixStack.push();

            IBakedModel model = itemRenderer.getItemModelWithOverrides(itemStack, workbenchTile.getWorld(), null);
            if (model.isGui3d()) {
                matrixStack.translate(0.5, 1.125, 0.5);
                matrixStack.scale(.5f, .5f, .5f);
            } else {
                matrixStack.translate(0.5, 1.0125, 0.5);
                matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
                matrixStack.scale(0.375f, 0.375f, 0.375f);
            }

            Minecraft.getInstance().getItemRenderer().renderItem(itemStack, ItemCameraTransforms.TransformType.FIXED,
                    WorldRenderer.getCombinedLight(workbenchTile.getWorld(), workbenchTile.getPos().up()),
                    combinedOverlay, matrixStack, buffer);

            matrixStack.pop();
        }
    }
}
