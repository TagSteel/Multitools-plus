package com.tagsteel.modtest;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class BasicMultitoolItem extends BaseMultitoolItem {

    public BasicMultitoolItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            if (!level.isClientSide) {
                // Changement de mode
                ToolMode currentMode = getMode(stack);
                ToolMode nextMode;

                switch (currentMode) {
                    case PICKAXE:
                        nextMode = ToolMode.AXE;
                        break;
                    case AXE:
                        nextMode = ToolMode.SHOVEL;
                        break;
                    default:
                        nextMode = ToolMode.PICKAXE;
                        break;
                }

                setMode(stack, nextMode);
                player.displayClientMessage(Component.translatable("message.multitoolsplus.mode_changed", nextMode.getDisplayName())
                    .withStyle(ChatFormatting.GREEN), true);
            }
            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.pass(stack);
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState state) {
        ToolMode mode = getMode(ItemStack.EMPTY);
        if (mode == ToolMode.PICKAXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE)) {
            return true;
        } else if (mode == ToolMode.AXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE)) {
            return true;
        } else if (mode == ToolMode.SHOVEL && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL)) {
            return true;
        }
        return false;
    }

    @Override
    protected float getEfficiency() {
        return Tiers.IRON.getSpeed();
    }

    @Override
    protected void addSpecificTooltips(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.multitoolsplus.basic_description").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("tooltip.multitoolsplus.modes", "Pioche, Hache, Pelle").withStyle(ChatFormatting.GRAY));
    }
}

