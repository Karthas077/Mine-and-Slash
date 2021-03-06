package com.robertx22.mine_and_slash.new_content.auto_comp;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.testing.Watch;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class DeterminePowerLevels {

    public static HashMap<GearItemSlot, List<PowerLevel>> MAP = new HashMap<>();
    public static HashMap<GearItemSlot, PowerLevel> STRONGEST = new HashMap<>();

    public static void setupHashMaps() {

        Watch watch = new Watch();

        Set<GearItemSlot> types = new HashSet<>(SlashRegistry.GearTypes()
            .getList());

        types.forEach(x -> {
            MAP.put(x, new ArrayList<>());
        });

        ForgeRegistries.ITEMS.getValues()
            .stream()
            .filter(x -> x.getRegistryName() != null && !Ref.MODID.equals(x.getRegistryName()
                .getNamespace()))
            .forEach(item -> {
                try {

                    types
                        .forEach(slot -> {
                            if (GearItemSlot.isGearOfThisType(slot, item)) {

                                PowerLevel current = new PowerLevel(item, slot);

                                MAP.get(slot)
                                    .add(current);

                                PowerLevel strongest = STRONGEST.getOrDefault(slot, new PowerLevel(item, slot));

                                if (current.isStrongerThan(strongest)) {
                                    strongest = current;
                                }

                                STRONGEST.put(slot, strongest);

                            }
                        });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        watch.print("[Setting up auto compatibility config power levels] ");

    }
}
