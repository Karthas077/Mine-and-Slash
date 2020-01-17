package com.robertx22.mine_and_slash.database.spells.spell_classes;

import com.robertx22.mine_and_slash.database.spells.items.ItemBonusEleAtkDmg;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.EffectCalculation;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.potion_effects.all.BonusDmgPotion;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SpellBonusEleBasicDmg extends BaseSpell implements IGenerated<BaseSpell> {

    public SpellBonusEleBasicDmg(Elements element) {
        this.element = element;
    }

    public Elements element = Elements.Physical;

    @Override
    public SpellType getSpellType() {
        return SpellType.Self_Buff;
    }

    @Override
    public String GUID() {
        return element.dmgName.toLowerCase() + "bonus_basic_atk_dmg_spell";
    }

    @Override
    public int getManaCost() {
        return 50;
    }

    @Override
    public int useTimeTicks() {
        return 20;
    }

    @Override
    public int getBaseValue() {
        return 5;
    }

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(new ElementalSpellDamage(element), 0.5F);
    }

    @Override
    public Elements getElement() {
        return element;
    }

    @Override
    public Item getSpellItem() {
        return ItemBonusEleAtkDmg.MAP.get(element);
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return CLOC.tooltip("ele_bonus_atk_dmg_spell");
    }

    @Override
    public boolean cast(World world, PlayerEntity caster, Hand hand, int ticksInUse,
                        SpellItemData data) {

        int amp = data.getDamage(Load.Unit(caster).getUnit());

        caster.addPotionEffect(new EffectInstance(BonusDmgPotion.MAP.get(element), 120 * 20, amp));

        return true;
    }

    @Override
    public List<BaseSpell> generateAllPossibleStatVariations() {

        List<BaseSpell> spells = new ArrayList<>();
        Elements.getAllSingleElements()
                .forEach(x -> spells.add(new SpellBonusEleBasicDmg(x)));

        return spells;
    }
}