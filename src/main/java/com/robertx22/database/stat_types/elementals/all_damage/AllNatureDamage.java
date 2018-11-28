package com.robertx22.database.stat_types.elementals.all_damage;

import com.robertx22.uncommon.enumclasses.Elements;

public class AllNatureDamage extends AllEleDamageBase {
	public static String GUID = "All Nature Damage";

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public Elements Element() {
		return Elements.Nature;
	}

}