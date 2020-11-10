package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.util.AllForms;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class EnsconcePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(EnsconcePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/EnsconcePower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/EnsconcePower32.png");

    public EnsconcePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        //AbstractDungeon.actionManager.cardsPlayedThisCombat
        ArrayList<AbstractCard> cardsThisTurn = new ArrayList<>(AbstractDungeon.actionManager.cardsPlayedThisTurn);
        boolean ensconce = true;
        for(AbstractCard c : cardsThisTurn) {
            if(AllForms.getAllForms().contains(c.cardID)) {
                ensconce = false;
            }
        }
        if(ensconce) {
            ArrayList<AbstractPower> playerPowers = new ArrayList<>(owner.powers);
            for(AbstractPower p : playerPowers) {
                if(AllForms.getAllFormsPowerIDs().contains(p.ID)) {
                    owner.getPower(p.ID).stackPower(amount);
                }
            }
        }

    }

    @Override
    public AbstractPower makeCopy() {
        return new EnsconcePower(owner, source, amount);
    }
}


