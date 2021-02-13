//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package StSShapeShifter.relics;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.AbstractShapeShifterCard;
import StSShapeShifter.powers.FreeFormPower;
import StSShapeShifter.util.AllForms;
import StSShapeShifter.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Random;

import static StSShapeShifter.ShapeshifterMod.makeRelicPath;

public class MysticalFossil extends CustomRelic {
    public static final String ID = ShapeshifterMod.makeID(MysticalFossil.class.getSimpleName());
    private static final String PNG = ".png";
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath(MysticalFossil.class.getSimpleName() + PNG));

    public MysticalFossil() {
        super(ID, IMG, RelicTier.RARE, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return AbstractDungeon.player != null ? this.setDescription(AbstractDungeon.player.chosenClass) : this.setDescription((PlayerClass)null);
    }

    private String setDescription(PlayerClass c) {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.flash();
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        ArrayList<AbstractCard> forms = new ArrayList<AbstractCard>(AllForms.getAllFormsCards());
        AbstractCard c = forms.get(new Random().nextInt(forms.size()));
        if(c instanceof AbstractShapeShifterCard) {
            ((AbstractShapeShifterCard) c).ModifiedCostCode = 2;
        }
        ShapeshifterMod.logger.info(c.cardID);
        c.costForTurn = 0;
        c.exhaust = true;
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, m));
    }
    public AbstractRelic makeCopy() {
        return new MysticalFossil();
    }

}



