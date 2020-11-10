//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllForms;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class FrogOfVitality extends AbstractRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(FrogOfVitality.class.getSimpleName());
    public ArrayList<String> formsPlayed = new ArrayList<>();

    public FrogOfVitality() {
        super(ID, "sunflower.png", RelicTier.UNCOMMON, LandingSound.SOLID);
    }

/*    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return "After obtaining this relic, whenever you play a unique form for this run, gain 1 max hp.";
    }*/

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(!formsPlayed.contains(targetCard.cardID) && AllForms.getAllForms().contains(targetCard.cardID)) {
            formsPlayed.add(targetCard.cardID);
            this.flash();
            AbstractDungeon.player.increaseMaxHp(1, true);
        }
    }

    public AbstractRelic makeCopy() {
        return new FrogOfVitality();
    }

}





