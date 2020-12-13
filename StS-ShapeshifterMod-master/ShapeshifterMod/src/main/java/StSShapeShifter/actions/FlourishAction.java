//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.actions;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.AbstractShapeShifterCard;
import StSShapeShifter.characters.ShapeShifter;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class FlourishAction extends AbstractGameAction {
    public boolean upgraded;
    public FlourishAction(boolean upgraded, int amount) {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
        this.upgraded = upgraded;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
                this.isDone = true;
                return;
            }
            if (AbstractDungeon.player.drawPile.isEmpty()){
                this.addToTop(new FlourishAction(this.upgraded, this.amount));
                this.addToTop(new EmptyDeckShuffleAction());

                this.isDone = true;
                return;
            }
            if(!AbstractDungeon.player.drawPile.isEmpty()) {
                reduceTopCardCost(upgraded);
            }

            this.isDone = true;
        }
    }

    private void reduceTopCardCost(boolean upgraded) {
        AbstractCard c = AbstractDungeon.player.drawPile.getTopCard();
        if(!upgraded) {
            this.addToBot(new ReduceCostForTurnAction(c, 1));
            if(c instanceof AbstractShapeShifterCard) {
                ((AbstractShapeShifterCard) c).ModifiedCostCode = 1;
            }
        }
        else {
            this.addToBot(new ReduceCostForTurnAction(c, 9));
            if(c instanceof AbstractShapeShifterCard) {
                ((AbstractShapeShifterCard) c).ModifiedCostCode = 2;
            }
        }
    }
}

