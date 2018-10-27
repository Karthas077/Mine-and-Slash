package com.robertx22.database.stats.types.elementals.resist;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.UsableStat;
import com.robertx22.stats.StatEffects.ElementalResistEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class FireResist extends UsableStat implements IStatEffects {
	public static String GUID = "Fire Resist";

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new ElementalResistEffect());
	}

	@Override
	public float MaximumPercent() {
		return 0.75F;
	}

	@Override
	public int AverageStat() {
		return 35;
	}

	public FireResist() {
	}

	@Override
	public String Name() {
		return GUID;
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Fire;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}