//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.actions;

import StSShapeShifter.cards.AbstractShapeShifterCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class StarAction extends AbstractGameAction {
    public boolean upgraded;
    public StarAction(int amount) {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
                this.isDone = true;
                return;
            }
            if (AbstractDungeon.player.drawPile.isEmpty()){
                this.addToTop(new StarAction(this.amount));
                this.addToTop(new EmptyDeckShuffleAction());

                this.isDone = true;
                return;
            }
            if(!AbstractDungeon.player.drawPile.isEmpty()) {
                reduceTopCardCost();
            }

            this.isDone = true;
        }
    }

    private void reduceTopCardCost() {
        AbstractCard c = AbstractDungeon.player.drawPile.getTopCard();
        this.addToBot(new ReduceCostForTurnAction(c, 9));
        if(c instanceof AbstractShapeShifterCard) {
            ((AbstractShapeShifterCard) c).ModifiedCostCode = 2;
        }
    }
}

