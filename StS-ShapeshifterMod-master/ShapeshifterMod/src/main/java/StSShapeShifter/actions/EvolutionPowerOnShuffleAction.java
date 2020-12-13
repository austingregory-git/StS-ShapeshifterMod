//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.actions;

import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class EvolutionPowerOnShuffleAction extends AbstractGameAction {
    public EvolutionPowerOnShuffleAction(int amount) {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
        this.amount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            ShapeshifterMod.logger.info("hello");
            ArrayList<AbstractCard> upgradableCards = new ArrayList();
            Iterator var2 = AbstractDungeon.player.drawPile.group.iterator();
            if(!AbstractDungeon.player.drawPile.group.isEmpty()) {
                while (var2.hasNext()) {
                    AbstractCard c = (AbstractCard) var2.next();
                    if (c.canUpgrade()) {
                        upgradableCards.add(c);
                    }
                }
                if (upgradableCards.size() > 0) {
                    Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
                    if(upgradableCards.size() < amount) {
                        amount = upgradableCards.size();
                    }
                    for(int i=0;i<amount; i++) {
                        ((AbstractCard)upgradableCards.get(i)).upgrade();
                        ((AbstractCard)upgradableCards.get(i)).superFlash();
                        ((AbstractCard)upgradableCards.get(i)).applyPowers();
                    }
                }
            }
            this.isDone = true;
        }

    }
}
