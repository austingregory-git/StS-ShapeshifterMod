//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllFruit;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Random;

public class FruitTree extends AbstractRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID("FruitTree");

    public FruitTree() {
        super(ID, "sunflower.png", RelicTier.UNCOMMON, LandingSound.SOLID);
    }

/*    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return "At the start of each combat, add a random fruit to your hand.";
    }*/

    public void atBattleStart() {
        ArrayList<AbstractCard> fruit = new ArrayList<AbstractCard>(AllFruit.getAllFruitCards());
        AbstractCard c = fruit.get(new Random().nextInt(fruit.size()));
        this.addToBot(new MakeTempCardInHandAction(c, 1));
    }

    public AbstractRelic makeCopy() {
        return new FruitTree();
    }

}





