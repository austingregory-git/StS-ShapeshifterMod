package StSShapeShifter.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.DefaultRareAttack;
import StSShapeShifter.util.TextureLoader;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class HydraFormPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID("HydraFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean upgraded;
    public int upgraded_amount;
    public int amount;
    private int count;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/placeholder_power32.png");

    public HydraFormPower(final AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        /*this.upgraded_amount = upgraded_amount;
        this.upgraded = upgraded;*/
        //this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 3.0F : damage;
    }

    public void onInitialApplication() {
        this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, -amount), -amount));
    }

    public void atEndOfTurn(boolean isPlayer) {
        count++;
        if(count == 3) {
            this.addToBot(new ChangeStanceAction("Neutral"));
        }
    }
    /*@Override
    public void duringTurn() {
        if(upgraded) {
            this.addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, 3), 3));
        }
        else {
            this.addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, 2), 2));
        }
    }*/

    @Override
    public void updateDescription() {

    }

    @Override
    public AbstractPower makeCopy() {
        return new LynxFormPower(owner, upgraded, upgraded_amount, amount);
    }
}





