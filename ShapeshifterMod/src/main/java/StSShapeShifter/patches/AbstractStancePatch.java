package StSShapeShifter.patches;

import StSShapeShifter.cards.EagleForm;
import StSShapeShifter.cards.Tortoise_Form;
import StSShapeShifter.stances.*;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class AbstractStancePatch {
    @SpirePatch(clz = AbstractStance.class,
            method = "getStanceFromName")
    public static class getStanceFromName {
        public static SpireReturn<AbstractStance> Prefix(String stanceID) {
            if (stanceID.equals(TortoiseFormStance.STANCE_ID)) {
                return SpireReturn.Return(new TortoiseFormStance());
            }
            if (stanceID.equals(LynxFormStance.STANCE_ID)) {
                return SpireReturn.Return(new LynxFormStance());
            }
            if (stanceID.equals(DeerFormStance.STANCE_ID)) {
                return SpireReturn.Return(new DeerFormStance());
            }
            if (stanceID.equals(BearFormStance.STANCE_ID)) {
                return SpireReturn.Return(new BearFormStance());
            }
            if (stanceID.equals(DragonFormStance.STANCE_ID)) {
                return SpireReturn.Return(new DragonFormStance());
            }
            if (stanceID.equals(HydraFormStance.STANCE_ID)) {
                return SpireReturn.Return(new HydraFormStance());
            }
            if (stanceID.equals(MammothFormStance.STANCE_ID)) {
                return SpireReturn.Return(new MammothFormStance());
            }
            if (stanceID.equals(RatFormStance.STANCE_ID)) {
                return SpireReturn.Return(new RatFormStance());
            }
            if (stanceID.equals(PorcupineFormStance.STANCE_ID)) {
                return SpireReturn.Return(new PorcupineFormStance());
            }
            if (stanceID.equals(EntFormStance.STANCE_ID)) {
                return SpireReturn.Return(new EntFormStance());
            }
            if (stanceID.equals(PhoenixFormStance.STANCE_ID)) {
                return SpireReturn.Return(new PhoenixFormStance());
            }
            if (stanceID.equals(EagleFormStance.STANCE_ID)) {
                return SpireReturn.Return(new EagleFormStance());
            }
            if (stanceID.equals(LionFormStance.STANCE_ID)) {
                return SpireReturn.Return(new LionFormStance());
            }
            if (stanceID.equals(MonkeyFormStance.STANCE_ID)) {
                return SpireReturn.Return(new LionFormStance());
            }
            if (stanceID.equals(SquirrelFormStance.STANCE_ID)) {
                return SpireReturn.Return(new SquirrelFormStance());
            }
            if (stanceID.equals(FoxFormStance.STANCE_ID)) {
                return SpireReturn.Return(new FoxFormStance());
            }

            return SpireReturn.Continue();
        }
    }
}
