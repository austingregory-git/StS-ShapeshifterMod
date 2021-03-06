package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.actions.ModifyGrowAction;
import StSShapeShifter.cards.AbstractDynamicCard;
import StSShapeShifter.util.BloomCountUtils;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

public class BaskInBloomPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(BaskInBloomPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/BaskInBloomPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/BaskInBloomPower32.png");
    public int cardDraw;

    public BaskInBloomPower(final AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if(BloomCountUtils.getBloomCount() >= 20) {
            this.flash();
            this.addToBot(new DrawCardAction(1));
        }
        if(BloomCountUtils.getBloomCount() >= 10) {
            this.flash();
            growAllCards();
        }
    }

    public void growAllCards() {
        Iterator var1 = AbstractDungeon.player.drawPile.group.iterator();
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();
        Iterator var3 = AbstractDungeon.player.discardPile.group.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if(c instanceof AbstractDynamicCard && ((AbstractDynamicCard) c).growValue > 0) {
                this.addToBot(new ModifyGrowAction(c.uuid, this.amount));
            }
        }
        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if(c instanceof AbstractDynamicCard && ((AbstractDynamicCard) c).growValue > 0) {
                this.addToBot(new ModifyGrowAction(c.uuid, this.amount));
            }
        }
        while(var3.hasNext()) {
            AbstractCard c = (AbstractCard) var3.next();
            if(c instanceof AbstractDynamicCard && ((AbstractDynamicCard) c).growValue > 0) {
                this.addToBot(new ModifyGrowAction(c.uuid, this.amount));
            }
        }
    }

    /*public void onCardDraw(AbstractCard card) {
        //card.getClass().getName().equals(AbstractDynamicCard.class.getName())
        if(card instanceof AbstractDynamicCard && BloomCountUtils.getBloomCount() >= 10){
            AbstractDynamicCard c = (AbstractDynamicCard) card;
            ShapeshifterMod.logger.info(c.growValue);
            ShapeshifterMod.logger.info(c);
            this.addToTop(new ModifyGrowAction(c.uuid, this.amount));
            c.applyPowers();
        }

    }*/

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount;
    }

    @Override
    public AbstractPower makeCopy() {
        return new BaskInBloomPower(owner, amount);
    }
}



