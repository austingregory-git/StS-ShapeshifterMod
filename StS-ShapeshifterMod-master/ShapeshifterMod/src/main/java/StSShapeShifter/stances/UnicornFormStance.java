package StSShapeShifter.stances;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.UnicornForm;
import StSShapeShifter.powers.UnicornFormPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class UnicornFormStance extends AbstractStance {
    public static final String STANCE_ID = UnicornFormStance.class.getSimpleName();
    public static final String NAME = "UnicornFormStance";
    private static final StanceStrings stanceString;
    private static long sfxId;
    private final AbstractPlayer owner;

    public UnicornFormStance() {
        this.ID = STANCE_ID;
        this.name = STANCE_ID;
        this.updateDescription();
        this.owner = AbstractDungeon.player;
    }

    public void updateDescription() {
        //this.description = stanceString.DESCRIPTION[0];
        this.description = "Unicorn";
    }

    public void onExitStance() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ShapeshifterMod.makeID(UnicornFormPower.class.getSimpleName())));
    }

    static {
        stanceString = CardCrawlGame.languagePack.getStanceString(UnicornFormStance.class.getSimpleName());
        sfxId = -1L;
    }
}




