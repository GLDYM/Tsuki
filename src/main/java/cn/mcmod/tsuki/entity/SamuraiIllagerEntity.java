package cn.mcmod.tsuki.entity;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.providers.VanillaEnchantmentProviders;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SamuraiIllagerEntity extends AbstractIllager implements GeoEntity {
    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.samurai_illager.idle");
    private static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.samurai_illager.walk");
    private static final RawAnimation AGGRESSIVE_IDLE = RawAnimation.begin()
            .thenLoop("animation.samurai_illager.aggressive_idle");
    private static final RawAnimation AGGRESSIVE_WALK = RawAnimation.begin()
            .thenLoop("animation.samurai_illager.aggressive_walk");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SamuraiIllagerEntity(EntityType<? extends SamuraiIllagerEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(11, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 24.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.FOLLOW_RANGE, 24.0);
    }

    @Override
    public AbstractIllager.IllagerArmPose getArmPose() {
        if (this.isAggressive()) {
            return AbstractIllager.IllagerArmPose.ATTACKING;
        }
        return this.isCelebrating()
                ? AbstractIllager.IllagerArmPose.CELEBRATING
                : AbstractIllager.IllagerArmPose.CROSSED;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
            MobSpawnType spawnType, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnData);
        RandomSource randomSource = level.getRandom();
        this.populateDefaultEquipmentSlots(randomSource, difficulty);
        this.populateDefaultEquipmentEnchantments(level, randomSource, difficulty);
        return spawnData;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        if (this.getCurrentRaid() == null) {
            float additionalDifficulty = difficulty.getSpecialMultiplier();
            if (random.nextFloat() < additionalDifficulty * 0.2F) {
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ArmorToolRegistry.TACHI.get()));
            } else {
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ArmorToolRegistry.KATANA.get()));
            }
        }
    }

    @Override
    protected void populateDefaultEquipmentEnchantments(ServerLevelAccessor level, RandomSource random,
            DifficultyInstance difficulty) {
        super.populateDefaultEquipmentEnchantments(level, random, difficulty);
        if (random.nextInt(3 + difficulty.getDifficulty().getId()) > 3) {
            ItemStack mainHand = this.getMainHandItem();
            EnchantmentHelper.enchantItemFromProvider(
                    mainHand, level.registryAccess(), VanillaEnchantmentProviders.MOB_SPAWN_EQUIPMENT, difficulty,
                    random);
        }
    }

    @Override
    public void applyRaidBuffs(ServerLevel level, int wave, boolean unused) {
        ItemStack weapon = new ItemStack(ArmorToolRegistry.TACHI.get());
        Raid raid = this.getCurrentRaid();
        if (raid != null) {
            boolean shouldEnchant = this.random.nextFloat() <= raid.getEnchantOdds();
            if (shouldEnchant) {
                ResourceKey<EnchantmentProvider> key = wave > raid
                        .getNumGroups(Difficulty.NORMAL)
                                ? VanillaEnchantmentProviders.RAID_VINDICATOR_POST_WAVE_5
                                : VanillaEnchantmentProviders.RAID_VINDICATOR;
                EnchantmentHelper.enchantItemFromProvider(
                        weapon, level.registryAccess(), key, level.getCurrentDifficultyAt(this.blockPosition()),
                        this.random);
            }
        }
        this.setItemSlot(EquipmentSlot.MAINHAND, weapon);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        this.setAggressive(this.getTarget() != null);
    }

    @Override
    public boolean isAlliedTo(Entity other) {
        if (super.isAlliedTo(other)) {
            return true;
        }
        if (other.getType().is(EntityTypeTags.ILLAGER_FRIENDS)) {
            return this.getTeam() == null && other.getTeam() == null;
        }
        return false;
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.VINDICATOR_CELEBRATE;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VINDICATOR_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VINDICATOR_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.VINDICATOR_HURT;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            boolean moving = state.isMoving();
            if (this.isAggressive()) {
                return state.setAndContinue(moving ? AGGRESSIVE_WALK : AGGRESSIVE_IDLE);
            }
            return state.setAndContinue(moving ? WALK : IDLE);
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
