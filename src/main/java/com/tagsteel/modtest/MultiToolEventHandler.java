package com.tagsteel.modtest;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MultiToolEventHandler {

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();
        BlockState state = event.getState();

        // Modification de la vitesse de casse en fonction du mode de l'outil
        if (heldItem.getItem() instanceof BaseMultitoolItem multitool) {
            BaseMultitoolItem.ToolMode mode = multitool.getMode(heldItem);
            float efficiency = 1.0F;

            if (heldItem.getItem() instanceof BasicMultitoolItem) {
                efficiency = mode == BaseMultitoolItem.ToolMode.PICKAXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE) ? 5.0F :
                            mode == BaseMultitoolItem.ToolMode.AXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE) ? 5.0F :
                            mode == BaseMultitoolItem.ToolMode.SHOVEL && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL) ? 5.0F : 1.0F;

            } else if (heldItem.getItem() instanceof AdvancedMultitoolItem) {
                efficiency = mode == BaseMultitoolItem.ToolMode.PICKAXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE) ? 7.0F :
                            mode == BaseMultitoolItem.ToolMode.AXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE) ? 7.0F :
                            mode == BaseMultitoolItem.ToolMode.SHOVEL && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL) ? 7.0F : 1.0F;

            } else if (heldItem.getItem() instanceof UltimateMultitoolItem) {
                efficiency = mode == BaseMultitoolItem.ToolMode.PICKAXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE) ? 9.0F :
                            mode == BaseMultitoolItem.ToolMode.AXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE) ? 9.0F :
                            mode == BaseMultitoolItem.ToolMode.SHOVEL && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL) ? 9.0F : 1.0F;
            }

            if (efficiency > 1.0F) {
                event.setNewSpeed(event.getOriginalSpeed() * efficiency);
            }
        }
    }

    @SubscribeEvent
    public void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();

        if (heldItem.getItem() instanceof BaseMultitoolItem multitool) {
            BaseMultitoolItem.ToolMode mode = multitool.getMode(heldItem);
            BlockState state = event.getTargetBlock();

            // Permet de miner avec le bon mode
            if ((mode == BaseMultitoolItem.ToolMode.PICKAXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE)) ||
                (mode == BaseMultitoolItem.ToolMode.AXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE)) ||
                (mode == BaseMultitoolItem.ToolMode.SHOVEL && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL))) {
                event.setCanHarvest(true);
            }
        }
    }
}

