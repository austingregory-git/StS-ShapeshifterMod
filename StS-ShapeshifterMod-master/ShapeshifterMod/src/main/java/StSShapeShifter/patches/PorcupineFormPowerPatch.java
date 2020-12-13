package StSShapeShifter.patches;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.powers.PorcupineFormPower;
import StSShapeShifter.util.BloomCountUtils;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PorcupineFormPowerPatch {
    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = "update"
    )
    public static class ApplyPowerAction_PorcupineImmunity {
        public static SpireReturn Prefix(ApplyPowerAction __instance, AbstractPower ___powerToApply) {
            try {
                if (__instance.target != null && __instance.target.hasPower(ShapeshifterMod.makeID(PorcupineFormPower.class.getSimpleName())) && ___powerToApply.type == AbstractPower.PowerType.DEBUFF) {
                    CardCrawlGame.sound.play("NULLIFY_SFX");
                    //___duration -= Gdx.graphics.getDeltaTime();
                    __instance.target.getPower(ShapeshifterMod.makeID(PorcupineFormPower.class.getSimpleName())).flashWithoutSound();
                    __instance.isDone = true;
                    return SpireReturn.Return(null);
                }
            } catch (NullPointerException e) {
                return SpireReturn.Continue();
            }
            return SpireReturn.Continue();
        }
    }
}
