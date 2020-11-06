package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class FlamingoFormPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(FlamingoFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean upgraded;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/FlamingoFormPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/FlamingoFormPower32.png");

    public FlamingoFormPower(final AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        //this.upgraded = upgraded;
        //this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();

        this.isTurnBased = false;
    }

    @Override
    public void atStartOfTurn() {
        Iterator var1 = this.owner.powers.iterator();
        ArrayList<AbstractPower> debuffs = new ArrayList<>();
        while(var1.hasNext()) {
            AbstractPower p = (AbstractPower)var1.next();
            if(p.type == PowerType.DEBUFF) {
                debuffs.add(p);
            }
        }
        if(!debuffs.isEmpty()) {
            AbstractPower debuffToRemove = debuffs.get(new Random().nextInt(debuffs.size()));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, debuffToRemove.ID));
        }

    }

    @Override
    public void updateDescription() {

    }

    @Override
    public AbstractPower makeCopy() {
        return new FlamingoFormPower(owner, amount);
    }
}



