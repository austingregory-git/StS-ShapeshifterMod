package StSShapeShifter.powers;

import StSShapeShifter.cards.AbstractShapeShifterCard;
import StSShapeShifter.util.AllForms;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.TextureLoader;

import java.util.Iterator;

public class FreeFormPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(FreeFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int count = 0;
    public AbstractPlayer player;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/FreeFormPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/FreeFormPower32.png");

    public FreeFormPower(final AbstractCreature owner, final int amount) {
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
    public void atStartOfTurn() {
        Iterator var1 = AbstractDungeon.player.drawPile.group.iterator();
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();
        Iterator var3 = AbstractDungeon.player.discardPile.group.iterator();

        while(var1.hasNext()) {
            AbstractCard card = (AbstractCard) var1.next();
            if (AllForms.getAllForms().contains(card.cardID) && !card.purgeOnUse && this.amount > 0) {
                card.setCostForTurn(-1);
            }
        }
        while(var2.hasNext()) {
            AbstractCard card = (AbstractCard) var2.next();
            if (AllForms.getAllForms().contains(card.cardID) && !card.purgeOnUse && this.amount > 0) {
                card.setCostForTurn(-1);
            }
        }
        while(var3.hasNext()) {
            AbstractCard card = (AbstractCard) var3.next();
            if (AllForms.getAllForms().contains(card.cardID) && !card.purgeOnUse && this.amount > 0) {
                card.setCostForTurn(-1);
            }
        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (AllForms.getAllForms().contains(card.cardID) && !card.purgeOnUse && this.amount > 0) {
            card.setCostForTurn(-1);
        }
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        ShapeshifterMod.logger.info(card.costForTurn);
        if (AllForms.getAllForms().contains(card.cardID) && !card.purgeOnUse && this.amount > 0) {
            count++;
            if(count == amount) {
                Iterator var2 = AbstractDungeon.player.hand.group.iterator();

                while(var2.hasNext()) {
                    AbstractCard c = (AbstractCard)var2.next();
                    if(c instanceof AbstractShapeShifterCard) {
                        if(AllForms.getAllForms().contains(c.cardID) && c.costForTurn == 0 && ((AbstractShapeShifterCard) c).ModifiedCostCode == 0) {
                            c.setCostForTurn(c.cost);
                            c.isCostModifiedForTurn = false;
                        }
                    }
                }
                this.addToBot(new RemoveSpecificPowerAction(owner, owner, FreeFormPower.POWER_ID));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new FreeFormPower(owner, amount);
    }
}

