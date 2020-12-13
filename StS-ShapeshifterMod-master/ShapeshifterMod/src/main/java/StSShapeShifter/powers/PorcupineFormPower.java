package StSShapeShifter.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.TextureLoader;

public class PorcupineFormPower extends AbstractFormPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID("PorcupineFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/PorcupineFormPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/PorcupineFormPower32.png");

    public PorcupineFormPower(final AbstractCreature owner, int amount) {
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

    public void onInitialApplication() {
        //this.addToBot(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, 1)));
        this.addToBot(new ApplyPowerAction(owner, owner, new ThornsPower(owner, amount), amount));
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        this.flash();
        this.addToBot(new ApplyPowerAction(owner, owner, new ThornsPower(owner, stackAmount), stackAmount));
    }

/*public void onRemove() {
        this.addToBot(new ApplyPowerAction(owner, owner, new ThornsPower(owner, -amount), -amount));
        ShapeshifterMod.logger.info(owner.hasPower("Thorns"));
        if(owner.hasPower("Thorns")) {
            int thorns_amount = owner.getPower("Thorns").amount;
            ShapeshifterMod.logger.info(thorns_amount);
            if(thorns_amount == amount) {
                this.addToBot(new RemoveSpecificPowerAction(owner, owner, "Thorns"));
                ShapeshifterMod.logger.info(owner.hasPower("Thorns"));
            }
        }
    }*/

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new PorcupineFormPower(owner, amount);
    }
}




