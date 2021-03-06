package StSShapeShifter.powers;

import StSShapeShifter.actions.ModifyWitherAction;
import StSShapeShifter.cards.AbstractDynamicCard;
import StSShapeShifter.cards.AbstractWitherCard;
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
        Iterator var1 = AbstractDungeon.player.drawPile.group.iterator();
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();
        Iterator var3 = AbstractDungeon.player.discardPile.group.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if(c instanceof AbstractWitherCard && ((AbstractWitherCard) c).witherValue > 0) {
                c.modifyCostForCombat(-9);
                this.addToTop(new ModifyWitherAction(c.uuid, ((AbstractWitherCard) c).baseWitherValue));
                ((AbstractWitherCard) c).ModifiedCostCode = 3;
                c.applyPowers();
            }
        }
        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if(c instanceof AbstractWitherCard && ((AbstractWitherCard) c).witherValue > 0) {
                c.modifyCostForCombat(-9);
                this.addToTop(new ModifyWitherAction(c.uuid, ((AbstractWitherCard) c).baseWitherValue));
                ((AbstractWitherCard) c).ModifiedCostCode = 3;
                c.applyPowers();
            }
        }
        while(var3.hasNext()) {
            AbstractCard c = (AbstractCard) var3.next();
            if(c instanceof AbstractWitherCard && ((AbstractWitherCard) c).witherValue > 0) {
                c.modifyCostForCombat(-9);
                this.addToTop(new ModifyWitherAction(c.uuid, ((AbstractWitherCard) c).baseWitherValue));
                ((AbstractWitherCard) c).ModifiedCostCode = 3;
                c.applyPowers();
            }
        }
    }

    public void onCardDraw(AbstractCard card) {
        //card.getClass().getName().equals(AbstractDynamicCard.class.getName())
        if(card instanceof AbstractWitherCard && ((AbstractWitherCard) card).witherValue > 0){
            AbstractWitherCard c = (AbstractWitherCard) card;
            //change to only wither cards
            c.modifyCostForCombat(-9);
            ShapeshifterMod.logger.info(c.witherValue);
            ShapeshifterMod.logger.info(c.ModifiedCostCode);
            if(c.ModifiedCostCode != 3)
                this.addToTop(new ModifyWitherAction(c.uuid, c.baseWitherValue));
            ShapeshifterMod.logger.info(c.witherValue);
            c.applyPowers();
            ShapeshifterMod.logger.info(c.witherValue);

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



