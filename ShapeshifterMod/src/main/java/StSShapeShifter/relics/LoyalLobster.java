//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllForms;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Collections;

public class LoyalLobster extends AbstractRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(LoyalLobster.class.getSimpleName());
    public int count;

    public LoyalLobster() {
        super(ID, "sunflower.png", RelicTier.UNCOMMON, LandingSound.SOLID);
    }

/*    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return "If you stay in the same form for 3 turns, gain 3 Strength for this combat. Can only trigger once.";
    }*/

    @Override
    public void onPlayerEndTurn() {
        ArrayList<AbstractCard> cardsThisTurn = new ArrayList<>(AbstractDungeon.actionManager.cardsPlayedThisTurn);
        ArrayList<String> powerIDs = new ArrayList<>();
        for(AbstractCard c : cardsThisTurn) {
            if(AllForms.getAllForms().contains(c.cardID)) {
                count = 0;
            }
        }
        for(AbstractPower pid : AbstractDungeon.player.powers) {
            if(AllForms.getAllFormsPowerIDs().contains(pid.ID)) {
                count++;
            }
        }
        if(count == 3) {
            this.flash();
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 3)));
            count = 0;
            this.active = false;
        }
    }

    @Override
    public void atPreBattle() {
        this.count = 0;
        this.active = true;
    }

    public AbstractRelic makeCopy() {
        return new LoyalLobster();
    }

}





