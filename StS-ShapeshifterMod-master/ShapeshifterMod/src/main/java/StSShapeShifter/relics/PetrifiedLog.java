//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.actions.ModifyWitherAction;
import StSShapeShifter.cards.AbstractShapeShifterCard;
import StSShapeShifter.cards.AbstractWitherCard;
import StSShapeShifter.util.AllForms;
import StSShapeShifter.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class PetrifiedLog extends CustomRelic {
    public static final String ID = ShapeshifterMod.makeID(PetrifiedLog.class.getSimpleName());
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(PetrifiedLog.class.getSimpleName() + PNG));

    public PetrifiedLog() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.flash();
        Iterator var1 = AbstractDungeon.player.drawPile.group.iterator();
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();
        Iterator var3 = AbstractDungeon.player.discardPile.group.iterator();
        Iterator var4 = AbstractDungeon.player.exhaustPile.group.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if(c instanceof AbstractWitherCard && ((AbstractWitherCard) c).witherValue > 0) {
                int witherReduction = -((AbstractWitherCard) c).baseWitherValue/2;
                this.addToTop(new ModifyWitherAction(c.uuid, witherReduction));
                c.applyPowers();
            }
        }
        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if(c instanceof AbstractWitherCard && ((AbstractWitherCard) c).witherValue > 0) {
                int witherReduction = -((AbstractWitherCard) c).baseWitherValue/2;
                this.addToTop(new ModifyWitherAction(c.uuid, witherReduction));
                c.applyPowers();
            }
        }
        while(var3.hasNext()) {
            AbstractCard c = (AbstractCard) var3.next();
            if(c instanceof AbstractWitherCard && ((AbstractWitherCard) c).witherValue > 0) {
                int witherReduction = -((AbstractWitherCard) c).baseWitherValue/2;
                this.addToTop(new ModifyWitherAction(c.uuid, witherReduction));
                c.applyPowers();
            }
        }

        while(var4.hasNext()) {
            AbstractCard c = (AbstractCard) var4.next();
            if(c instanceof AbstractWitherCard && ((AbstractWitherCard) c).witherValue > 0) {
                int witherReduction = -((AbstractWitherCard) c).baseWitherValue/2;
                this.addToTop(new ModifyWitherAction(c.uuid, witherReduction));
                c.applyPowers();
            }
        }
    }
    private int getReductionToWitherValue(int witherVal, int witherReduction) {
        return witherVal / witherReduction + ((witherVal % witherReduction == 0) ? 0 : 1);
    }
    public AbstractRelic makeCopy() {
        return new PetrifiedLog();
    }

}



