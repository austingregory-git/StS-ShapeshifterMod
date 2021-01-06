package StSShapeShifter.actions;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.AbstractDynamicCard;
import basemod.patches.com.megacrit.cardcrawl.characters.AbstractPlayer.MaxHandSizePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class RockSlideAction extends AbstractGameAction {
    private UUID uuid;
    public AbstractCard card;
    public int count = 0;

    public RockSlideAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        ShapeshifterMod.logger.info(!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty());
        if(!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            ArrayList<AbstractCard> cardsThisTurn = new ArrayList<>(AbstractDungeon.actionManager.cardsPlayedThisTurn);
            ShapeshifterMod.logger.info(cardsThisTurn);
            ShapeshifterMod.logger.info(cardsThisTurn.contains(this.card));
            ShapeshifterMod.logger.info(AbstractDungeon.player.hand);
            ShapeshifterMod.logger.info("---");
            ShapeshifterMod.logger.info(AbstractDungeon.player.discardPile);
            if(cardsThisTurn.contains(this.card)) {
                count++;
                cardsThisTurn.remove(cardsThisTurn.size() - 1);
                ShapeshifterMod.logger.info(cardsThisTurn);
                ShapeshifterMod.logger.info(cardsThisTurn.contains(this.card));
                ShapeshifterMod.logger.info(count);
                if(!cardsThisTurn.contains(this.card) && count == 1) {
                    AbstractDungeon.player.hand.addToHand(this.card);
                    AbstractDungeon.player.discardPile.removeCard(this.card);
                    ShapeshifterMod.logger.info(AbstractDungeon.player.hand);
                    ShapeshifterMod.logger.info("---");
                    ShapeshifterMod.logger.info(AbstractDungeon.player.discardPile);
                    if(card instanceof AbstractDynamicCard) {
                        ((AbstractDynamicCard) card).applyGrow();
                    }
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.discardPile.refreshHandLayout();
                }
            }
        }

        this.isDone = true;
    }
}

