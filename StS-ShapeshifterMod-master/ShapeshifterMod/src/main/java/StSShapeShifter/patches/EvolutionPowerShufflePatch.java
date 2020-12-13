package StSShapeShifter.patches;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.EagleForm;
import StSShapeShifter.cards.Tortoise_Form;
import StSShapeShifter.cards.WaspSwarmForm;
import StSShapeShifter.powers.EvolutionPower;
import StSShapeShifter.stances.*;
import StSShapeShifter.util.BloomCountUtils;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class EvolutionPowerShufflePatch {
    @SpirePatch(clz = EmptyDeckShuffleAction.class,
            method = "update")

    public static class EmptyDeckShuffleAction_EvolutionPower {
        @SpireInsertPatch(
                loc = 51
        )
        public static void Insert() {
            if(AbstractDungeon.player.hasPower(ShapeshifterMod.makeID(EvolutionPower.class.getSimpleName()))) {
                EvolutionPower.onShuffle(AbstractDungeon.player.getPower(ShapeshifterMod.makeID(EvolutionPower.class.getSimpleName())).amount);
                ShapeshifterMod.logger.info(AbstractDungeon.player.getPower(ShapeshifterMod.makeID(EvolutionPower.class.getSimpleName())).amount);
            }
        }
    }
}

