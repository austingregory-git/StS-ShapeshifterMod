//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class AlchemistsNecklace extends AbstractRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID("AlchemistsNecklace");

    public AlchemistsNecklace() {
        super(ID, "sunflower.png", RelicTier.COMMON, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return "Whenever you use a potion, draw 2 cards.";
    }

    public void onUsePotion() {
        this.addToBot(new DrawCardAction(2));
    }

    public AbstractRelic makeCopy() {
        return new AlchemistsNecklace();
    }

}





