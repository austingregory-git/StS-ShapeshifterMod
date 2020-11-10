package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.AbstractDynamicCard;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class WolfsBanePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(WolfsBanePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/SpringPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/SpringPower32.png");
    public int growValue;
    public int witherValue;
    public int growAmount;
    public int witherAmount;
    public WolfsBanePower(final AbstractCreature owner, int growAmount, int witherAmount, int growValue, int witherValue) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.growAmount = growAmount;
        this.witherAmount = witherAmount;
        this.growValue = growValue;
        this.witherValue = witherValue;
        //this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card instanceof AbstractDynamicCard){
            AbstractDynamicCard c = (AbstractDynamicCard) card;
            if(c.growValue > 0) {
                this.flash();
                AbstractMonster targetMonster = AbstractDungeon.getRandomMonster();
                this.addToBot(new ApplyPowerAction(targetMonster, owner, new PoisonPower(targetMonster, owner, this.growAmount), this.growAmount));
                this.growAmount += this.growValue;
            }
            if(c.witherValue > 0) {
                this.flash();
                AbstractMonster targetMonster = AbstractDungeon.getRandomMonster();
                this.addToBot(new ApplyPowerAction(targetMonster, owner, new PoisonPower(targetMonster, owner, this.witherAmount), this.witherAmount));
                if(witherAmount > 0)
                    witherAmount -= witherValue;
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new WolfsBanePower(owner, growAmount, witherAmount, growValue, witherValue);
    }
}

