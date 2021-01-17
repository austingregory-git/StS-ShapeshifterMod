package StSShapeShifter.actions;

import StSShapeShifter.cards.AbstractDynamicCard;
import StSShapeShifter.cards.AbstractWitherCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.Iterator;
import java.util.UUID;

public class ModifyWitherAction extends AbstractGameAction {
    private UUID uuid;

    public ModifyWitherAction(UUID targetUUID, int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }

    public void update() {
        Iterator var1 = GetAllInBattleInstances.get(this.uuid).iterator();

        while(var1.hasNext()) {
            AbstractWitherCard c = (AbstractWitherCard)var1.next();
            c.baseWitherValue += this.amount;
            if (c.baseWitherValue < 0) {
                c.baseWitherValue = 0;
            }
        }

        this.isDone = true;
    }
}

