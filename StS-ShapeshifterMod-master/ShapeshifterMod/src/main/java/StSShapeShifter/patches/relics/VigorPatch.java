package StSShapeShifter.patches.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllForms;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.ArrayList;

public class VigorPatch {
    @SpirePatch(clz = VigorPower.class,
            method = "onUseCard")

    public static class RemoveFormStanceTips {
        @SpireInsertPatch(
                rloc = 0,
                localvars={"card"}
        )
        public static void Insert(VigorPower __instance, AbstractCard card) {
            ShapeshifterMod.logger.info(card);
            if((AllForms.getAllForms().contains(card.cardID) && card.baseDamage > 0)) {
                __instance.flash();
                ShapeshifterMod.logger.info(__instance.owner);
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(__instance.owner, __instance.owner, "Vigor"));
            }
        }
    }
}

