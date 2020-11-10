package StSShapeShifter.powers;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.actions.EvolutionPowerOnShuffleAction;
import StSShapeShifter.util.AllForms;
import StSShapeShifter.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.*;

public class EvolutionPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID(EvolutionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static int count;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/EvolutionPower84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/EvolutionPower32.png");

    public EvolutionPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        //this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

    }

    /*public void onCardDraw(AbstractCard card) {
        ShapeshifterMod.logger.info(AbstractDungeon.player.drawPile);
        this.addToBot(new EmptyDeckShuffleAction());
        if(AbstractDungeon.player.drawPile.size() == 1) {
            ShapeshifterMod.logger.info("hello");
            ArrayList<AbstractCard> upgradableCards = new ArrayList();
            Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

            while (var2.hasNext()) {
                AbstractCard c = (AbstractCard) var2.next();
                if (c.canUpgrade() && AllForms.getAllForms().contains(c.cardID)) {
                    upgradableCards.add(c);
                }
            }

            if (upgradableCards.size() > 0) {
                Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
                for(int i=0;i<this.amount; i++) {
                    ((AbstractCard)upgradableCards.get(i)).upgrade();
                    ((AbstractCard)upgradableCards.get(i)).superFlash();
                    ((AbstractCard)upgradableCards.get(i)).applyPowers();
                }

            }
        }
    }*/

    public static void onShuffle(int amount) {
        if(count == 0) {
            AbstractDungeon.actionManager.addToBottom(new EvolutionPowerOnShuffleAction(amount));
        }
        count++;
    }

    public void atStartOfTurnPostDraw() {
        count = 0;
    }

    @Override
    public AbstractPower makeCopy() {
        return new EvolutionPower(owner, amount);
    }
}



