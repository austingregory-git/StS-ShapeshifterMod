package StSShapeShifter.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.DefaultRareAttack;
import StSShapeShifter.util.TextureLoader;

import java.util.Iterator;

public class PhoenixFormPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID("PhoenixFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int count = 0;
    private boolean upgraded;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/placeholder_power32.png");

    public PhoenixFormPower(final AbstractCreature owner, boolean upgraded) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.upgraded = upgraded;
        //this.amount = amount;
        //this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    /*public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        return damage-this.amount;
    }*/

    public int onLoseHp(int damageAmount) {
        if(AbstractDungeon.player.currentHealth - damageAmount <= 0) {
            int damageBeyondZero = Math.abs(AbstractDungeon.player.currentHealth - damageAmount);
            float percent;
            if(upgraded)
                percent = 30.0F / 100.0F;
            else
                percent = 20.0F / 100.0F;

            int healAmt = (int)((float)AbstractDungeon.player.maxHealth * percent) + damageBeyondZero;
            if (healAmt < 1) {
                healAmt = 1;
            }
            AbstractDungeon.player.heal(healAmt, true);
            this.addToBot(new ChangeStanceAction("Neutral"));
        }
        return damageAmount;
    }

/*    public void onDeath() {

    }*/

    public void atEndOfTurn(boolean isPlayer) {
        count++;
        if(count == 3) {
            this.addToBot(new ChangeStanceAction("Neutral"));
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
        return new PhoenixFormPower(owner, upgraded);
    }
}




