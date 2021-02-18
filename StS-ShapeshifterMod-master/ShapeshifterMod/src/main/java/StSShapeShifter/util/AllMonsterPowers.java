package StSShapeShifter.util;

import StSShapeShifter.cards.*;
import StSShapeShifter.stances.BearFormStance;
import StSShapeShifter.stances.DeerFormStance;
import StSShapeShifter.stances.LynxFormStance;
import StSShapeShifter.stances.TortoiseFormStance;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.*;

import java.util.ArrayList;
import java.util.Arrays;

public class AllMonsterPowers {
    public static ArrayList<String> getAllMonsterPowerIDs() {
        ArrayList<String> allMonsterPowers = new ArrayList<String>(Arrays.asList(AngryPower.POWER_ID, BackAttackPower.POWER_ID, CurlUpPower.POWER_ID,
                                                                            ExplosivePower.POWER_ID, BeatOfDeathPower.POWER_ID, CuriosityPower.POWER_ID));

        return allMonsterPowers;
    }

    public static ArrayList<String> getAllBossPowerIDs() {
        ArrayList<String> allBossPowers = new ArrayList<>(Arrays.asList(BeatOfDeathPower.POWER_ID, CuriosityPower.POWER_ID, InvinciblePower.POWER_ID, ModeShiftPower.POWER_ID, UnawakenedPower.POWER_ID, TimeWarpPower.POWER_ID, FadingPower.POWER_ID));
        return allBossPowers;
    }

    public static ArrayList<String> getAllViableMonsterPowers() {
        ArrayList<String> allMonsterPowers = new ArrayList<String>(Arrays.asList(AngryPower.POWER_ID, CurlUpPower.POWER_ID, ExplosivePower.POWER_ID, DeerForm.ID,
                                                                            MalleablePower.POWER_ID, SharpHidePower.POWER_ID, SporeCloudPower.POWER_ID,
                                                                            RitualPower.POWER_ID, ThornsPower.POWER_ID, MetallicizePower.POWER_ID, BarricadePower.POWER_ID,
                                                                            ArtifactPower.POWER_ID, StrengthPower.POWER_ID, IntangiblePower.POWER_ID, MetallicizePower.POWER_ID));

        return allMonsterPowers;
    }



}
