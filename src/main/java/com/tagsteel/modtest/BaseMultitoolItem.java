package com.tagsteel.modtest;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseMultitoolItem extends TieredItem {

    public enum ToolMode {
        PICKAXE("Pioche"),
        AXE("Hache"),
        SHOVEL("Pelle"),
        SWORD("Épée"),
        SPECIAL("Spécial");

        private final String displayName;

        ToolMode(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    protected float baseAttackDamage;
    protected float baseAttackSpeed;

    public BaseMultitoolItem(Properties properties) {
        super(Tiers.IRON, properties);
        this.baseAttackDamage = 2.0F;
        this.baseAttackSpeed = -2.8F;
    }

    public ToolMode getMode(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        int ordinal = tag.getInt("ToolMode");
        ToolMode[] modes = ToolMode.values();
        return modes[Math.min(ordinal, modes.length - 1)];
    }

    public void setMode(ItemStack stack, ToolMode mode) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("ToolMode", mode.ordinal());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        // Logique spécifique par mode pour useOn
        ToolMode mode = getMode(stack);

        // Implémentation spécifique dans les sous-classes
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        // Logique spécifique pour interagir avec les entités
        return InteractionResult.PASS;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ToolMode mode = getMode(stack);
        // Utiliser la durabilité en fonction du mode
        if (mode == ToolMode.SWORD) {
            stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            return true;
        }
        // Pénalité si utilisation d'un mauvais mode pour attaquer
        stack.hurtAndBreak(2, attacker, e -> e.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F) {
            stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        }
        return true;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        ToolMode mode = getMode(stack);

        if (mode == ToolMode.PICKAXE) {
            return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
        } else if (mode == ToolMode.AXE) {
            return net.minecraftforge.common.ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction);
        } else if (mode == ToolMode.SHOVEL) {
            return net.minecraftforge.common.ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction);
        } else if (mode == ToolMode.SWORD) {
            return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
        }

        return false;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        ToolMode mode = getMode(stack);

        if (mode == ToolMode.PICKAXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE)) {
            return getEfficiency();
        } else if (mode == ToolMode.AXE && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE)) {
            return getEfficiency();
        } else if (mode == ToolMode.SHOVEL && state.is(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL)) {
            return getEfficiency();
        }

        return 1.0F; // Vitesse réduite si ce n'est pas le bon outil
    }

    protected abstract float getEfficiency();

    @Override
    public boolean isCorrectToolForDrops(BlockState state) {
        // Implémenté dans les sous-classes
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        ToolMode mode = getMode(stack);
        tooltip.add(Component.translatable("tooltip.multitoolsplus.mode", mode.getDisplayName()).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("tooltip.multitoolsplus.mode_change").withStyle(ChatFormatting.AQUA));

        addSpecificTooltips(stack, level, tooltip, flag);
    }

    protected abstract void addSpecificTooltips(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag);

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        ToolMode mode = getMode(stack);
        if (mode == ToolMode.SWORD) {
            return UseAnim.BLOCK;
        }
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }
}

