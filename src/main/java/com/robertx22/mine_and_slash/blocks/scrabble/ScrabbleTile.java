package com.robertx22.mine_and_slash.blocks.scrabble;

import com.electronwill.nightconfig.core.utils.StringUtils;
import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModBlocks;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModTileEntities;
import com.robertx22.mine_and_slash.new_content.chests.MapChestTile;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class ScrabbleTile extends TileEntity implements ITickableTileEntity {

    public ScrabbleTile() {
        super(ModTileEntities.SCRABBLE.get());
    }

    String letters = "";
    int attemptsLeft = 3;

    static char[] alphabet = {
        'a',
        'b',
        'c',
        'd',
        'e',
        'f',
        'g',
        'h',
        'i',
        'j',
        'k',
        'l',
        'm',
        'n',
        'o',
        'p',
        'q',
        'r',
        's',
        't',
        'u',
        'v',
        'w',
        'x',
        'y',
        'z'
    };

    static char[] nonWovels = {
        'b',
        'c',
        'd',
        'f',
        'g',
        'h',
        'j',
        'k',
        'l',
        'm',
        'n',
        'p',
        'q',
        'r',
        's',
        't',
        'v',
        'w',
        'x',
        'y',
        'z'
    };

    static char[] wovels = {
        'a',
        'e',
        'i',
        'o',
        'u',
        };

    @Override
    public void tick() {
        if (!world.isRemote) {

            if (letters.isEmpty()) {
                this.letters = genRandomLetters();
            }
        }
    }

    public void tryGuessWord(String word) {

        word = word.toLowerCase(Locale.ROOT);

        ParticlePacketData data = new ParticlePacketData(pos, ParticleEnum.AOE).type(ParticleTypes.WITCH)
            .amount(50)
            .radius(1);

        if (usesAllowedLetters(word, letters)) {
            if (isInDict(word)) {

                this.world.setBlockState(pos, ModBlocks.MAP_CHEST.get()
                    .getDefaultState());

                TileEntity tile = world.getTileEntity(pos);

                ParticleEnum.AOE.sendToClients(
                    pos,
                    world,
                    data.type(ParticleTypes.HAPPY_VILLAGER));

                SoundUtils.playSound(world, pos, SoundEvents.ENTITY_VILLAGER_YES, 1, 1);

                if (tile instanceof MapChestTile) {

                    MapChestTile chest = (MapChestTile) tile;

                    NonNullList<ItemStack> genItems = NonNullList.create();

                    int amount = getItemRewardAmount(word, letters);

                    amount = MathHelper.clamp(amount, 1, 27);

                    for (int i = 0; i < amount; i++) {
                        LootCrate crate = SlashRegistry.LootCrates()
                            .random();
                        crate.generateItems(new LootInfo(world.getWorld(), pos))
                            .forEach(x -> genItems.add(x));
                    }

                    chest.addItems(genItems);

                }

                return;

            }
        }

        if (attemptsLeft > 1) {

            ParticleEnum.AOE.sendToClients(
                pos,
                world,
                data.type(ParticleTypes.WITCH));

            SoundUtils.playSound(world, pos, SoundEvents.ENTITY_VILLAGER_NO, 1, 1);
        } else {
            ParticleEnum.AOE.sendToClients(
                pos,
                world,
                data.type(ParticleTypes.FLAME)
                    .motion(new Vec3d(0, 0, 0)));

            SoundUtils.playSound(world, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, 1, 1);

            world.setBlockState(pos, Blocks.AIR.getDefaultState());

        }

        attemptsLeft--;

    }

    public static int getItemRewardAmount(String word, String letters) {

        float score = getScoreOfWordForLetters(word, letters);

        boolean isSameLength = word.length() == letters.length();

        int amount = (int) ((float) 3 * score);

        if (isSameLength) {
            amount *= 2;
        }
        if (word.length() < 4) {
            amount *= 0.5;
        }

        return amount;

    }

    // 0-1
    public static float getScoreOfWordForLetters(String word, String letters) {
        return (float) getScoreOfWord(word) / (float) getScoreOfWord(getHighestScoreWord(letters));
    }

    public static int getScoreOfWord(String word) {

        return word.length(); // todo maybe use scrabble's default scoring?
    }

    public static String getHighestScoreWord(String letters) {
        String best = WORDS.stream()
            .filter(w -> usesAllowedLetters(w, letters))
            .max(Comparator.comparingInt(x -> getScoreOfWord(x)))
            .get();

        return best;
    }

    public static String genRandomLetters() {

        String letters = "";

        int tries = 0;

        while (!areLettersSuitable(letters) || tries < 500) {
            letters = RandomUtils.randomFromList(LONG_WORDS);

            tries++;
        }

        letters = scramble(new Random(), letters);

        return letters;

    }

    public static String scramble(Random random, String inputString) {
        // Convert your string into a simple char array:
        char a[] = inputString.toCharArray();

        // Scramble the letters using the standard Fisher-Yates shuffle,
        for (int i = 0; i < a.length; i++) {
            int j = random.nextInt(a.length);
            // Swap letters
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        return new String(a);
    }

    public static boolean areLettersSuitable(String letters) {

        if (letters.length() < 2) {
            return false;
        }

        int words = 0;

        Set<ImmutablePair<String, Integer>> pairs = getPairs(letters);

        for (String x : WORDS) {
            if (x.length() > 2 && usesAllowedLetters(x, letters, pairs)) {
                words++;
            }
        }

        return words > 3 && words < 5 * letters.length();
    }

    public static Set<ImmutablePair<String, Integer>> getPairs(String letters) {

        Set<ImmutablePair<String, Integer>> pairs = new HashSet<>();

        for (int i = 0; i < letters.length(); i++) {
            String letter = String.valueOf(letters
                .charAt(i));
            int count = countLetter(letters, letter);
            pairs.add(ImmutablePair.of(letter, count));

        }

        return pairs;
    }

    public static boolean usesAllowedLetters(String word, String letters) {

        Set<ImmutablePair<String, Integer>> pairs = getPairs(letters);

        return usesAllowedLetters(word, letters, pairs);
    }

    public static boolean usesAllowedLetters(String word, String letters, Set<ImmutablePair<String, Integer>> pairs) {

        for (int i = 0; i < word.length(); i++) {
            String letter = String.valueOf(word.charAt(i));

            if (!letters
                .contains(letter)) {
                return false;
            }
        }

        for (ImmutablePair<String, Integer> pair : pairs) {
            int count = countLetter(word, pair.left);

            if (count > pair.right) {
                return false;
            }
        }

        return true;

    }

    public static int countLetter(String string, String letter) {
        int count = 0;

        for (int c = 0; c < string.length(); c++) {
            String l = String.valueOf(string.charAt(c));
            if (l.equals(letter)) {
                count++;
            }
        }

        return count;
    }

    public static boolean isInDict(String word) {
        return WORDS.contains(word);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);

        nbt.putString("letters", this.letters);
        nbt.putInt("attempts", this.attemptsLeft);
        return nbt;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);

        this.letters = nbt.getString("letters");
        this.attemptsLeft = nbt.getInt("attempts");
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT updateTagDescribingTileEntityState = getUpdateTag();
        final int METADATA = 0;
        return new SUpdateTileEntityPacket(this.pos, METADATA, updateTagDescribingTileEntityState);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT updateTagDescribingTileEntityState = pkt.getNbtCompound();
        handleUpdateTag(updateTagDescribingTileEntityState);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

    public static List<String> WORDS = new ArrayList<>();
    public static List<String> LONG_WORDS = new ArrayList<>();

    public static void loadWordList() {

        InputStream input = MMORPG.class.getClassLoader()
            .getResourceAsStream("assets\\mmorpg\\word_list.txt");

        try {
            String file = IOUtils.toString(input, "utf-8");
            WORDS = StringUtils.splitLines(file);

            WORDS = WORDS.stream()
                .filter(x -> x != null && !x.isEmpty())
                .map(x -> x.toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());

            LONG_WORDS = WORDS.stream()
                .filter(x -> x.length() > 5)
                .collect(Collectors.toList());

            System.out.println("Loaded " + WORDS.size() + " words to the dictionary.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
