package StSShapeShifter.patches;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.powers.EvolutionPower;
import StSShapeShifter.util.AllForms;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;

public class RemoveFormStanceTipsPatch {
    @SpirePatch(clz = AbstractPlayer.class,
            method = "renderPowerTips")

    public static class RemoveFormStanceTips {
        @SpireInsertPatch(
                rloc = 6,
                localvars={"tips"}
        )
        public static void Insert(ArrayList<PowerTip> tips) {
            tips.removeIf(pt -> AllForms.getAllFormsStanceNames().contains(pt.header));
        }
    }
}

