package StSShapeShifter.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sun.security.provider.ConfigFile;

@SpirePatch(    // "Use the @SpirePatch annotation on the patch class."
        clz = AbstractDungeon.class, // This is the class where the method we will be patching is. In our case - Abstract Dungeon
        method = SpirePatch.CLASS // This is the name of the method we will be patching.
        /*
        Now let's imagine for a second that there were two methods named returnRandomRelicKey()
        The one we're patching - "String returnRandomRelicKey(RelicTier tier)" - that grabs a relic of specific tier
        and a fictional one - "String returnRandomRelicKey(RelicTier tier, LandingSound sound)" - that grabs a relic of a specific tier AND with a specific landing sound.
        How would we tell the code which of the two methods to put our patch in? We use paramtypez (read the docs too they have a good example!)
        Let's say we wanted to patch the second fictional one - we would add
        paramtypez={
                AbstractRelic.RelicTier.class,
                AbstractRelic.LandingSound.class
        }
        to this annotation, after the method parameter. (If we wanted to patch the first one, we'd only put "AbstractRelic.RelicTier.class".
        */
)
public class BloomWiltCountField {
    /*@SpireInsertPatch(
            loc=261,
            localvars={"bloomCount, wiltCount"}
    )
    public static void AddBloomWiltCounter(int bloomCount, int wiltCount) {
        bloomCount = 0;
        wiltCount = 0;
    }*/
    public static SpireField<Integer> bloomCount = new SpireField<>(() -> 0);
    public static SpireField<Integer> wiltCount = new SpireField<>(() -> 0);

   /* public static class bloomCountSpireField extends SpireField<Integer> {
        bloomCountSpireField(DefaultValue<Integer> defaultValue) {
            super(defaultValue);
        }

        public void set(Object __instance, int amount) {
            super.set(__instance, amount);
        }
        public int get() {
            return ;
        }
    }*/
}
