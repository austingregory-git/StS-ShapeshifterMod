package StSShapeShifter.powers;

import StSShapeShifter.actions.ModifyWitherAction;
import StSShapeShifter.cards.AbstractDynamicCard;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.TextureLoader;

import java.util.Iterator;

public class WinterPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(WinterPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/WinterPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/WinterPower32.png");
    public boolean upgraded;

    public WinterPower(final AbstractCreature owner, boolean upgraded) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        //this.amount = amount;
        this.upgraded = upgraded;
        //this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if(c instanceof AbstractDynamicCard && ((AbstractDynamicCard) c).witherValue > 0) {
                if(upgraded) {
                    this.addToBot(new ReduceCostForTurnAction(c, 1));
                }
                else
                    this.addToBot(new ReduceCostForTurnAction(c, 9));

                this.addToTop(new ModifyWitherAction(c.uuid, ((AbstractDynamicCard) c).baseWitherValue));
                c.applyPowers();
            }
        }
    }

    public void onCardDraw(AbstractCard card) {
        //card.getClass().getName().equals(AbstractDynamicCard.class.getName())
        if(card instanceof AbstractDynamicCard){
            AbstractDynamicCard c = (AbstractDynamicCard) card;
            //change to only wither cards
            if(c.witherValue>0) {
                if(upgraded) {
                    this.addToBot(new ReduceCostForTurnAction(c, 1));
                }
                else
                    this.addToBot(new ReduceCostForTurnAction(c, 9));

                this.addToTop(new ModifyWitherAction(c.uuid, c.baseWitherValue));
                c.applyPowers();
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new WinterPower(owner, upgraded);
    }
}



