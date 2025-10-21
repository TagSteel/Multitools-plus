package com.tagsteel.modtest;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.List;

public class UltimateMultitoolItem extends BaseMultitoolItem {


    public UltimateMultitoolItem(Properties properties) {
        super(properties);
        this.baseAttackDamage = 6.0F;
        this.baseAttackSpeed = -2.2F;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            if (!level.isClientSide) {
                // Cycle through all modes including SPECIAL
                ToolMode currentMode = getMode(stack);
                ToolMode nextMode;

                switch (currentMode) {
                    case PICKAXE:
                        nextMode = ToolMode.AXE;
                        break;
                    case AXE:
                        nextMode = ToolMode.SHOVEL;
                        break;
                    case SHOVEL:
                        nextMode = ToolMode.SWORD;
                        break;
                    case SWORD:
                        nextMode = ToolMode.SPECIAL;
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
        } else if (getMode(stack) == ToolMode.SPECIAL) {
            // Mode spécial: scan de minerais rares
            if (!level.isClientSide) {
                scanForValuableOres(level, player);
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
            }
            return InteractionResultHolder.success(stack);
        } else if (getMode(stack) == ToolMode.SWORD && !player.isShiftKeyDown()) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }

        return InteractionResultHolder.pass(stack);
    }

    private void scanForValuableOres(Level level, Player player) {
        int radius = MultitoolsConfig.ORE_DETECTOR_RADIUS.get();
        BlockPos playerPos = player.blockPosition();
        int count = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = playerPos.offset(x, y, z);
                    BlockState state = level.getBlockState(pos);

                    if (isValuableOre(state)) {
                        count++;
                        // Afficher des particules sur le client
                        level.sendBlockUpdated(pos, state, state, 3);
                    }
                }
            }
        }

        if (count > 0) {
            player.displayClientMessage(Component.translatable("message.multitoolsplus.ore_detected", count)
                .withStyle(ChatFormatting.GOLD), true);
        } else {
            player.displayClientMessage(Component.translatable("message.multitoolsplus.no_ore_detected")
                .withStyle(ChatFormatting.GRAY), true);
        }
    }

    private boolean isValuableOre(BlockState state) {
        return state.is(Tags.Blocks.ORES_DIAMOND) ||
               state.is(Tags.Blocks.ORES_EMERALD) ||
               state.is(Tags.Blocks.ORES_GOLD) ||
               state.is(Tags.Blocks.ORES_REDSTONE) ||
               state.is(Tags.Blocks.ORES_LAPIS);
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
        return Tiers.NETHERITE.getSpeed() + 1.0F;
    }



    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return super.canPerformAction(stack, toolAction);
    }

    @Override
    protected void addSpecificTooltips(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.multitoolsplus.ultimate_description").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("tooltip.multitoolsplus.modes", "Pioche, Hache, Pelle, Épée, Spécial").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("tooltip.multitoolsplus.ultimate_feature").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltip.add(Component.translatable("tooltip.multitoolsplus.auto_smelt").withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("tooltip.multitoolsplus.ore_detection").withStyle(ChatFormatting.AQUA));
    }
}

