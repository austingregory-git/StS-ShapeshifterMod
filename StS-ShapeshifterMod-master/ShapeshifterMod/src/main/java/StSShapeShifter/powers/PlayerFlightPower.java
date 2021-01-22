package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.awt.*;

public class PlayerFlightPower extends AbstractFormPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(PlayerFlightPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    //private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/MammothFormPower84.png");
    //private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/MammothFormPower32.png");

    public PlayerFlightPower(final AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;

        // We load those textures here.
        this.loadRegion("flight");
        //this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        //this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void atEndOfRound() {
        this.flash();
        ShapeshifterMod.logger.info(this.amount);
        if (this.amount == 0) {
            ShapeshifterMod.logger.info("removing flight");
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, PlayerFlightPower.POWER_ID));
        } else {
            ShapeshifterMod.logger.info(PlayerFlightPower.POWER_ID);
            this.addToBot(new ReducePowerAction(this.owner, this.owner, PlayerFlightPower.POWER_ID, 1));
        }

    }

    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        return this.calculateDamageTakenAmount(damage, type);
    }

    private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type) {
        return type != DamageInfo.DamageType.HP_LOSS && type != DamageInfo.DamageType.THORNS ? damage / 2.0F : damage;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new PlayerFlightPower(owner, amount);
    }
}



