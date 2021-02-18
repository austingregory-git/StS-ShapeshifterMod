package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.tempCards.Cherry;
import StSShapeShifter.cards.tempCards.RottenFruit;
import StSShapeShifter.util.AllFruit;
import StSShapeShifter.util.BloomCountUtils;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;
import java.util.Random;

public class GardenPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(GardenPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/GardenPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/GardenPower32.png");
    public boolean upgraded;

    public GardenPower(final AbstractCreature owner, boolean upgraded) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.upgraded = upgraded;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if(BloomCountUtils.getBloomCount() >= -3 && BloomCountUtils.getBloomCount() <= 3) {
            ArrayList<AbstractCard> fruit = new ArrayList<AbstractCard>(AllFruit.getAllFruitCards());
            ArrayList<AbstractCard> cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisCombat;
            for(AbstractCard c: cardsPlayed) {
                if(c.cardID.equals(Cherry.ID)) {
                    fruit = new ArrayList<>(AllFruit.getAllNonHealingFruitCards());
                }
            }
            if(upgraded) {
                this.addToBot(new MakeTempCardInHandAction(fruit.get(new Random().nextInt(fruit.size())), 1));
                this.addToBot(new MakeTempCardInHandAction(fruit.get(new Random().nextInt(fruit.size())), 1));
            }
            else {
                this.addToBot(new MakeTempCardInHandAction(fruit.get(new Random().nextInt(fruit.size())), 1));
            }
        }
        else if(BloomCountUtils.getBloomCount() >= 10) {
            if(upgraded) {
                this.addToBot(new GainEnergyAction(1));
                this.addToBot(new DrawCardAction(1));
            }
            else {
                this.addToBot(new GainEnergyAction(1));
            }

        }
        else if(BloomCountUtils.getBloomCount() <= -10) {
            if(upgraded) {
                this.addToBot(new MakeTempCardInHandAction(new RottenFruit()));
                this.addToBot(new MakeTempCardInHandAction(new RottenFruit()));
            }
            else {
                this.addToBot(new MakeTempCardInHandAction(new RottenFruit()));
            }
        }
    }

    @Override
    public void updateDescription() {
        if(upgraded) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1];
        }

    }

    @Override
    public AbstractPower makeCopy() {
        return new GardenPower(owner, upgraded);
    }
}



