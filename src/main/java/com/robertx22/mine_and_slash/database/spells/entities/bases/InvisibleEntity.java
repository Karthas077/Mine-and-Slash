package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class InvisibleEntity extends Entity {

    private UUID casterUUID;

    public int lifetime = 600;

    public InvisibleEntity(EntityType type, World world) {
        super(type, world);

        this.setNoGravity(true);
        this.setMotion(new Vec3d(0, 0, 0));
        this.setInvulnerable(true);
    }

    @Override
    public void tick() {

        if (this.ticksExisted > lifetime && lifetime != -1) {
            this.remove();
        }

        super.tick();

    }

    @Override
    protected void readAdditional(CompoundNBT p_70037_1_) {

    }

    @Override
    protected void writeAdditional(CompoundNBT p_213281_1_) {

    }

    public UUID getOwnerId() {
        return casterUUID;
    }

    @Nullable
    public LivingEntity getCaster() {
        return Utilities.getLivingEntityByUUID(world, getOwnerId());

    }

    public void setCaster(LivingEntity caster) {
        this.casterUUID = caster.getUniqueID();
    }

    public boolean isValidTarget(Entity target) {
        return true;
    }

    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

}