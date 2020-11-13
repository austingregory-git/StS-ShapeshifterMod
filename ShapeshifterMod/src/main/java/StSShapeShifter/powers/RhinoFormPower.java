package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import java.util.Iterator;

public class RhinoFormPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(RhinoFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int count = 0;
    public boolean upgraded;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/RhinoFormPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/RhinoFormPower32.png");

    public RhinoFormPower(final AbstractCreature owner, boolean upgraded) {
        name = NAME;
        ID = POWER_ID;

        this.upgraded = upgraded;
        this.owner = owner;
        //this.amount = amount;
        //this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void onInitialApplication() {
        if(upgraded) {
            this.addToBot(new GainBlockAction(this.owner, 22));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner, 10)));
        } else {
            this.addToBot(new GainBlockAction(this.owner, 18));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner, 8)));
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.addToBot(new GainBlockAction(this.owner, stackAmount));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner, stackAmount)));
    }

    public void atEndOfTurn(boolean isPlayer) {
        AbstractMonster weakestMonster = null;
        Iterator var4 = AbstractDungeon.getMonsters().monsters.iterator();

        while(var4.hasNext()) {
            AbstractMonster m = (AbstractMonster)var4.next();
            if (!m.isDeadOrEscaped()) {
                if (weakestMonster == null) {
                    weakestMonster = m;
                } else if (m.currentHealth < weakestMonster.currentHealth) {
                    weakestMonster = m;
                }
            }
        }
        this.addToBot(new DamageAction(weakestMonster, new DamageInfo(this.owner, AbstractDungeon.player.currentBlock, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SMASH));
        count++;
        if(count == 3) {
            this.addToBot(new ChangeStanceAction("Neutral"));
        }
    }

    @Override
    public void updateDescription() {
        if(upgraded)
            description = DESCRIPTIONS[0] + (3-count) + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[1] + (3-count) + DESCRIPTIONS[2];

    }

    @Override
    public AbstractPower makeCopy() {
        return new RhinoFormPower(owner, upgraded);
    }
}



