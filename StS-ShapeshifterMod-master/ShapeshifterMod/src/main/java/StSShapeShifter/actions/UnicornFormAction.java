//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.actions;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.AbstractShapeShifterCard;
import StSShapeShifter.patches.EnumsPatch;
import StSShapeShifter.util.AllForms;
import StSShapeShifter.util.AllGrowWitherCards;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class UnicornFormAction extends AbstractGameAction {
    public boolean upgraded;
    private AbstractPlayer p;
    public UnicornFormAction(boolean upgraded) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.upgraded = upgraded;
        this.p = AbstractDungeon.player;

        //this.upgraded = upgraded;
        //this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.group.size() <= 0) {
                this.isDone = true;
            } else {
                CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                Iterator var2 = this.p.hand.group.iterator();

                while(var2.hasNext()) {
                    AbstractCard c = (AbstractCard)var2.next();
                    //if upgraded, cost > 0, and not a status, it is valid
                    if (upgraded && c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce && c.canUpgrade() && c.type != AbstractCard.CardType.STATUS) {
                        validCards.addToTop(c);
                    }
                    //otherwise, if it isn't upgraded but the other conditions apply, still valid
                    else if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce && c.type != AbstractCard.CardType.STATUS) {
                        validCards.addToTop(c);
                    }
                    //otherwise, if a card can be upgraded but not reduced, still add it to valid cards
                    else if(upgraded && c.canUpgrade() && c.type != AbstractCard.CardType.STATUS) {
                        validCards.addToTop(c);
                    }
                }

                if (validCards.size() > 0) {
                    validCards.shuffle();
                    if(upgraded)
                        ((AbstractCard)validCards.group.get(0)).upgrade();
                    ((AbstractCard)validCards.group.get(0)).setCostForTurn(0);
                    if(((AbstractCard)validCards.group.get(0)) instanceof AbstractShapeShifterCard) {
                        ((AbstractShapeShifterCard) validCards.group.get(0)).ModifiedCostCode = 2;
                    }
                    ((AbstractCard)validCards.group.get(0)).superFlash();
                    ((AbstractCard)validCards.group.get(0)).applyPowers();
                }

                this.isDone = true;
            }
        } else {
            this.tickDuration();
        }
    }

}


