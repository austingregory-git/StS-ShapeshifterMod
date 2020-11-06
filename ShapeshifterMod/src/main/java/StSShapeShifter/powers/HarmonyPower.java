package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.BloomCountUtils;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.Random;

public class HarmonyPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(HarmonyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/BurrBombPower32.png");
    public boolean upgraded;

    public HarmonyPower(final AbstractCreature owner, boolean upgraded) {
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
            if(upgraded) {
                this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, 1), 1));
                this.addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, 1), 1));
            }
            else {
                Random r = new Random();
                int num = r.nextInt(2);
                if(num == 0) {
                    this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, 1), 1));
                }
                else {
                    this.addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, 1), 1));
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new HarmonyPower(owner, upgraded);
    }
}



