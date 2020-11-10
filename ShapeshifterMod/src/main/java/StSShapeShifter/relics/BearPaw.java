//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllFruit;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Random;

public class BearPaw extends AbstractRelic {
    public boolean active;
    public static final String ID = ShapeshifterMod.makeID(BearPaw.class.getSimpleName());

    public BearPaw() {
        super(ID, "sunflower.png", RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public void atPreBattle() {
        ArrayList<AbstractRelic> relics = AbstractDungeon.player.relics;
    }

    public AbstractRelic makeCopy() {
        return new BearPaw();
    }

}





