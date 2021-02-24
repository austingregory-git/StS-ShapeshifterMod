//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.actions;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.AbstractShapeShifterCard;
import StSShapeShifter.powers.PorcupineFormPower;
import StSShapeShifter.stances.PorcupineFormStance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.watcher.CannotChangeStancePower;

import java.util.Iterator;

public class PorcupineFormAction extends AbstractGameAction {
    public boolean upgraded;
    private AbstractPlayer p;
    public PorcupineFormAction(int amount) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
        this.p = AbstractDungeon.player;

        //this.upgraded = upgraded;
        //this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if(!p.stance.ID.equals(PorcupineFormStance.STANCE_ID) && !p.hasPower(CannotChangeStancePower.POWER_ID)) {
                this.addToBot(new ChangeStanceAction(PorcupineFormStance.STANCE_ID));
                this.addToBot(new ApplyPowerAction(p, p, new PorcupineFormPower(p, this.amount)));
                CardCrawlGame.sound.play(ShapeshifterMod.makeID("SFX_PorcupineForm"));
            }
            else {
                this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, this.amount)));
            }
            this.isDone = true;
        } else {
            this.tickDuration();
        }
    }

}


