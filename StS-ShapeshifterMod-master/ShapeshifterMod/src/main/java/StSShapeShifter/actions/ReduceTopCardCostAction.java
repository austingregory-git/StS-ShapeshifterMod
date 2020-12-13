//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.actions;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.ShapeShifter;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class ReduceTopCardCostAction extends AbstractGameAction {
    public boolean upgraded;
    public ReduceTopCardCostAction(boolean upgraded) {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
        this.upgraded = upgraded;
        //this.amount = amount;
    }

    public void update() {
        if(!upgraded) {
            //AbstractDungeon.player.drawPile.shuffle();
            AbstractCard c = AbstractDungeon.player.drawPile.getTopCard();
            ShapeshifterMod.logger.info("Card on top of draw pile:" + c);
            c.isCostModifiedForTurn = true;
            c.setCostForTurn(c.cost - 1);
            c.applyPowers();

        }
        else {
            AbstractCard c = AbstractDungeon.player.drawPile.getTopCard();
            c.isCostModifiedForTurn = true;
            c.setCostForTurn(-9);
            //this.addToBot(new DrawCardAction(1));
        }
    }
}


