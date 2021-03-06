package StSShapeShifter.powers;

import StSShapeShifter.actions.FoxDigAction;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.TextureLoader;

public class FoxFormPower extends AbstractFormPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(FoxFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int count = 0;
    public boolean upgraded;
    public AbstractPlayer player;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/FoxFormPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/FoxFormPower32.png");

    public FoxFormPower(final AbstractPlayer player, final AbstractCreature owner, boolean upgraded) {
        name = NAME;
        ID = POWER_ID;

        this.player = player;
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

    @Override
    public void atStartOfTurn() {
        this.addToBot(new FoxDigAction(player, owner, upgraded));
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
        return new FoxFormPower(player, owner, upgraded);
    }
}



