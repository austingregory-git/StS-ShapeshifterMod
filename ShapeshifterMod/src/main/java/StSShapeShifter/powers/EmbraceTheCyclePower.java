package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllFruit;
import StSShapeShifter.util.BloomCountUtils;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Random;

public class EmbraceTheCyclePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(EmbraceTheCyclePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/EmbraceTheCyclePower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/EmbraceTheCyclePower32.png");
    public boolean upgraded;

    public EmbraceTheCyclePower(final AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if(BloomCountUtils.getBloomCount() >= -3 && BloomCountUtils.getBloomCount() <= 3 && card.type == AbstractCard.CardType.POWER) {
            card.setCostForTurn(card.cost - 1);
        }
        if(BloomCountUtils.getBloomCount() <= -10 && card.type == AbstractCard.CardType.ATTACK) {
            card.setCostForTurn(card.cost - 1);
        }
        if(BloomCountUtils.getBloomCount() >= 10 && card.type == AbstractCard.CardType.SKILL) {
            card.setCostForTurn(card.cost - 1);
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new EmbraceTheCyclePower(owner, amount);
    }
}



