package com.robertx22.mine_and_slash.database;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

public interface IGUID {

    public String GUID();

    public default String formattedGUID() {
        return formatString(GUID());

    }

    public static void runTest() {

        String formatted = IGUID.getformattedString("DamageFlat");
        String finalstr = "damage_flat";

        if (!formatted.equals(finalstr)) {
            throw new RuntimeException("GUID formatter is incorrect");
        }
        formatted = IGUID.getformattedString("DAMageFLAt");
        finalstr = "damage_flat";

        if (!formatted.equals(finalstr)) {
            throw new RuntimeException("GUID formatter is incorrect");
        }
    }

    public default String formatString(String str) {
        return getformattedString(str);
    }

    public default String getFormatedForLangFile(String str) {
        return str.replaceAll(" ", "_").toLowerCase(Locale.ROOT).replaceAll("/", ".").replaceAll(":", ".");
    }

    public static String getformattedString(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return addUnderscoresBehindUppercase(str).replaceAll(" ", "_").toLowerCase(Locale.ROOT);
    }

    static String addUnderscoresBehindUppercase(String str) {
        String s = "";

        int i = 0;

        boolean addedUnderscore = true;

        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c) && i > 0 && !addedUnderscore) {
                s += "_" + c;
                addedUnderscore = true;
            } else {
                s += c;
                if (!Character.isUpperCase(c)) {
                    addedUnderscore = false;
                }
            }

            i++;

        }
        return s;

    }

    default Item getFromForgeRegistry() {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(Ref.MODID, GUID()));
    }

    default boolean isGuidFormattedCorrectly() {
        return isGUIDFormattedCorrectly(GUID());
    }

    public static boolean isGUIDFormattedCorrectly(String id) {
        for (char c : id.toCharArray()) {
            if (!isValidPathCharacter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidPathCharacter(char c) {
        return c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c == '_' || c == ':' || c == '/' || c == '.' || c == '-';
    }

}
