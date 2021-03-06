package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.stances.LynxFormStance;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class LynxFormPower extends AbstractFormPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(LynxFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean upgraded;
    public int upgraded_amount;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/LynxFormPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/LynxFormPower32.png");

    public LynxFormPower(final AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        ShapeshifterMod.logger.info(powerStrings);

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, stackAmount), stackAmount));
    }

    public void onInitialApplication() {
        this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
    }

    public void onRemove() {
        this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, -amount), -amount));
    }

    /*@Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);
        if (this.img != null) {
            sb.setColor(c);
            sb.draw(this.img, owner.hb.cX - 64.0F, owner.hb.cY + 128.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 128, 128, false, false);
        } else {
            sb.setColor(c);
            sb.draw(this.region128, owner.hb.cX - 64.0F, owner.hb.cY + 96.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F);
        }
    }*/

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new LynxFormPower(owner, amount);
    }
}




