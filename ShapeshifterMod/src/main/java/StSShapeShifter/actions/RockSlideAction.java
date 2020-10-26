package StSShapeShifter.actions;

import StSShapeShifter.ShapeshifterMod;
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

    public RockSlideAction(UUID targetUUID, AbstractCard card) {
        this.actionType = ActionType.WAIT;
        this.card = card;
        this.uuid = targetUUID;
    }

    public void update() {
        if(!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && !AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            ArrayList<AbstractCard> cardsThisTurn = new ArrayList<>(AbstractDungeon.actionManager.cardsPlayedThisTurn);
            cardsThisTurn.remove(cardsThisTurn.size() - 1);
            if(!cardsThisTurn.contains(card)) {
                ShapeshifterMod.logger.info(!cardsThisTurn.contains(card));
                //AbstractDungeon.player.hand.moveToHand(card);
                AbstractDungeon.player.hand.addToHand(card);
            }
        }

        this.isDone = true;
    }
}

