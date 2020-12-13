package StSShapeShifter.patches;

import StSShapeShifter.util.BloomCountUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class resetBloomCountPatch {
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "resetPlayer"
    )
    public static class AbstractDungeon_resetBloomCount {
        @SpirePostfixPatch
        public static void patch() {
            BloomCountUtils.setBloomCount(0);
        }
    }
}
