package com.robertx22.mine_and_slash.database.stats.types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.ElementalInfusionEffect;
import com.robertx22.mine_and_slash.database.stats.types.SingleElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

import java.util.List;

public class ElementalInfusion extends SingleElementalStat implements IStatEffects {

    public static MapWrapper<Elements, ElementalInfusion> MAP = new MapWrapper();

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = super.generateAllPossibleStatVariations();
        list.forEach(x -> MAP.put(x.getElement(), (ElementalInfusion) x));
        return list;

    }

    public ElementalInfusion(Elements element) {
        super(element);
    }

    @Override
    public Stat.StatGroup statGroup() {
        return Stat.StatGroup.EleAttackDamage;
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new ElementalInfusion(element);
    }

    @Override
    public String getIconPath() {
        return "spell_to_atk_dmg/" + element.guidName;
    }

    @Override
    public String locDescForLangFile() {
        return "A percent of your bonus to spell damage is added to your elemental attacks too.";
    }

    @Override
    public IStatEffect getEffect() {
        return new ElementalInfusionEffect();
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "ele_spell_to_attack_dmg";
    }

    public Stat StatThatGiveDamage() {
        return new ElementalSpellDamage(this.getElement());
    }

    @Override
    public String locNameForLangFile() {
        return this.getElement()
            .dmgName + " Infusion";
    }

    @Override
    public String GUID() {
        return getElement().guidName + "_spell_to_attack_dmg";
    }

}