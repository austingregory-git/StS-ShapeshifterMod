package StSShapeShifter.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.TextureLoader;

public class LionFormPower extends AbstractFormPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(LionFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int count = 0;
    public int amount;
    public boolean upgraded;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/LionFormPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/LionFormPower32.png");

    public LionFormPower(final AbstractCreature owner, int amount, boolean upgraded) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.upgraded = upgraded;
        //this.source = source;

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
    }

    @Override
    public void onInitialApplication() {
        if(this.owner.hasPower("Strength")) {
            if(upgraded) {
                int strToAdd = this.owner.getPower("Strength").amount * 2;
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, strToAdd)));
            }
            else {
                int strToAdd = this.owner.getPower("Strength").amount;
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, strToAdd)));
            }

        }
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            AbstractMonster targetMonster = AbstractDungeon.getRandomMonster();
            this.addToBot(new ApplyPowerAction(targetMonster, owner, new DeepWoundPower(targetMonster, amount)));
            this.flash();
        }
        count++;
        if(count == 3) {
            this.addToBot(new ChangeStanceAction("Neutral"));
        }
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + (3-count) + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new LionFormPower(owner, amount, upgraded);
    }
}



