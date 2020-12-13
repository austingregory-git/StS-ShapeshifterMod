package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.actions.ModifyWitherAction;
import StSShapeShifter.cards.AbstractDynamicCard;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AutumnPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(AutumnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/AutumnPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/AutumnPower32.png");
    public int cardDraw;

    public AutumnPower(final AbstractCreature owner, int amount, int cardDraw) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.cardDraw = cardDraw;
        //this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.isTurnBased = false;
        AbstractPlayer var10000 = AbstractDungeon.player;
        var10000.gameHandSize += cardDraw;

        updateDescription();
    }


    public void onCardDraw(AbstractCard card) {
        //card.getClass().getName().equals(AbstractDynamicCard.class.getName())
        if(card instanceof AbstractDynamicCard){
            AbstractDynamicCard c = (AbstractDynamicCard) card;
            this.addToTop(new ModifyWitherAction(c.uuid, this.amount));
            c.applyPowers();
        }
    }

    public void onRemove() {
        AbstractPlayer var10000 = AbstractDungeon.player;
        var10000.gameHandSize += cardDraw;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount;
    }

    @Override
    public AbstractPower makeCopy() {
        return new AutumnPower(owner, amount, cardDraw);
    }
}



