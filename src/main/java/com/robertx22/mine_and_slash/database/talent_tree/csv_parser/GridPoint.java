package com.robertx22.mine_and_slash.database.talent_tree.csv_parser;

import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class GridPoint<T extends BasePerk> {

    public int x;
    public int y;
    private String effectID;

    public String getEffectID() {
        return effectID.toLowerCase();
    }

    public String getEffectIDRaw() {
        return effectID;
    }

    public GridPoint(int x, int y, String str) {
        this.x = x;
        this.y = y;
        this.effectID = str;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    public T getPerk() {
        // handle both caps and lowercase
        String id = getID();

        if (!SlashRegistry.Perks().isRegistered(id)) {
            id = id.toLowerCase();
            if (!SlashRegistry.Perks().isRegistered(id)) {
                id = id.toUpperCase();
            }
        }

        return (T) SlashRegistry.Perks().get(id);
    }

    public boolean isTalent() {
        return !effectID.isEmpty() && effectID.length() > 2;
    }

    public boolean isConnector() {
        return effectID.equals("O") || effectID.equals("o");
    }

    public String getID() {
        return x + "_" + y;
    }

}
