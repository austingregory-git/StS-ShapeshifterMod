package StSShapeShifter.stances;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.StormGryphonForm;
import StSShapeShifter.powers.StormGryphonFormPower;
import StSShapeShifter.powers.UnicornFormPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class StormGryphonFormStance extends AbstractStance {
    public static final String STANCE_ID = StormGryphonFormStance.class.getSimpleName();
    private static final StanceStrings stanceString;
    private static long sfxId;
    private final AbstractPlayer owner;

    public StormGryphonFormStance() {
        this.ID = STANCE_ID;
        this.name = STANCE_ID;
        this.updateDescription();
        this.owner = AbstractDungeon.player;
    }

    public void updateDescription() {

    }

    public void onExitStance() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ShapeshifterMod.makeID(StormGryphonFormPower.class.getSimpleName())));
    }

    static {
        stanceString = CardCrawlGame.languagePack.getStanceString(StormGryphonFormStance.class.getSimpleName());
        sfxId = -1L;
    }
}




