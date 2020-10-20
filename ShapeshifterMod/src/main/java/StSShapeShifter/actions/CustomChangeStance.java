//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.actions;

import StSShapeShifter.stances.CustomAbstractStance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;
import java.util.Iterator;

public class CustomChangeStance extends AbstractGameAction {
    private String id;
    private AbstractStance newStance;

    public CustomChangeStance(String stanceId) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.id = stanceId;
        this.newStance = CustomAbstractStance.getStanceFromName(this.id);

    }

    public CustomChangeStance(AbstractStance newStance) {
        this(newStance.ID);
        this.newStance = newStance;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hasPower("CannotChangeStancePower")) {
                this.isDone = true;
                return;
            }

            AbstractStance oldStance = AbstractDungeon.player.stance;
            if (!oldStance.ID.equals(this.id)) {
                if (this.newStance == null) {
                    this.newStance = CustomAbstractStance.getStanceFromName(this.id);
                }

                Iterator var2 = AbstractDungeon.player.powers.iterator();

                while(var2.hasNext()) {
                    AbstractPower p = (AbstractPower)var2.next();
                    p.onChangeStance(oldStance, this.newStance);
                }

                var2 = AbstractDungeon.player.relics.iterator();

                while(var2.hasNext()) {
                    AbstractRelic r = (AbstractRelic)var2.next();
                    r.onChangeStance(oldStance, this.newStance);
                }

                oldStance.onExitStance();
                AbstractDungeon.player.stance = this.newStance;
                this.newStance.onEnterStance();
                if (AbstractDungeon.actionManager.uniqueStancesThisCombat.containsKey(this.newStance.ID)) {
                    int currentCount = (Integer)AbstractDungeon.actionManager.uniqueStancesThisCombat.get(this.newStance.ID);
                    AbstractDungeon.actionManager.uniqueStancesThisCombat.put(this.newStance.ID, currentCount + 1);
                } else {
                    AbstractDungeon.actionManager.uniqueStancesThisCombat.put(this.newStance.ID, 1);
                }

                AbstractDungeon.player.switchedStance();
                var2 = AbstractDungeon.player.discardPile.group.iterator();

                while(var2.hasNext()) {
                    AbstractCard c = (AbstractCard)var2.next();
                    c.triggerExhaustedCardsOnStanceChange(this.newStance);
                }

                AbstractDungeon.player.onStanceChange(this.id);
            }

            AbstractDungeon.onModifyPower();
            if (Settings.FAST_MODE) {
                this.isDone = true;
                return;
            }
        }

        this.tickDuration();
    }
}

