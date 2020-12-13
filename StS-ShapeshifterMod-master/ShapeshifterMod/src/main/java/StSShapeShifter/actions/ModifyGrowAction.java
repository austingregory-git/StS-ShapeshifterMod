package StSShapeShifter.actions;

import StSShapeShifter.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import java.util.Iterator;
import java.util.UUID;

public class ModifyGrowAction extends AbstractGameAction {
    private UUID uuid;

    public ModifyGrowAction(UUID targetUUID, int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }

    public void update() {
        Iterator var1 = GetAllInBattleInstances.get(this.uuid).iterator();

        while(var1.hasNext()) {
            AbstractDynamicCard c = (AbstractDynamicCard)var1.next();
            c.baseGrowValue += this.amount;
            if (c.baseGrowValue < 0) {
                c.baseGrowValue = 0;
            }
        }

        this.isDone = true;
    }
}

