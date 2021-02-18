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

public class FullMoonPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(FullMoonPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int count = 0;
    public AbstractPlayer player;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/FullMoonPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/FullMoonPower32.png");

    public FullMoonPower(final AbstractCreature owner, final AbstractPlayer player, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.player = player;
        //this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }

    @Override
    public void onInitialApplication() {
        if(!AbstractDungeon.player.hand.group.isEmpty()) {
            Iterator var2 = AbstractDungeon.player.hand.group.iterator();

            while(var2.hasNext()) {
                AbstractCard c = (AbstractCard)var2.next();
                if(AllForms.getAllForms().contains(c.cardID) && c instanceof AbstractShapeShifterCard) {
                    c.setCostForTurn(-6);
                    ((AbstractShapeShifterCard) c).ModifiedCostCode = 3;
                }
            }
        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (AllForms.getAllForms().contains(card.cardID) && !card.purgeOnUse && this.amount > 0 && card instanceof AbstractShapeShifterCard) {
            card.setCostForTurn(-6);
            ((AbstractShapeShifterCard) card).ModifiedCostCode = 3;
        }
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (AllForms.getAllForms().contains(card.cardID) && !card.purgeOnUse && this.amount > 0 && !AbstractDungeon.player.hasPower(FreeFormPower.POWER_ID) && (card instanceof AbstractShapeShifterCard && ((AbstractShapeShifterCard) card).ModifiedCostCode == 3) && card.cost != 0) {
            count++;
            this.reducePower(1);
            if(count == amount) {
                Iterator var2 = AbstractDungeon.player.hand.group.iterator();

                while(var2.hasNext()) {
                    AbstractCard c = (AbstractCard)var2.next();
                    if(AllForms.getAllForms().contains(c.cardID) && c.costForTurn == -6) {
                        c.setCostForTurn(c.cost);
                    }
                }
                this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            }
        }
    }

    @Override
    public void updateDescription() {
        if (count == 4) {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + (5-count) + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new FullMoonPower(owner, player, amount);
    }
}
