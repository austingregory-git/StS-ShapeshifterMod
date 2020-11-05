package StSShapeShifter.powers;

import StSShapeShifter.util.AllForms;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

import java.util.ArrayList;

public class DiverseSpiritPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = ShapeshifterMod.makeID("DiverseSpiritPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("StSShapeShifterResources/images/powers/placeholder_power32.png");
    ArrayList<String> uniqueForms;

    public DiverseSpiritPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        this.uniqueForms = new ArrayList<>();

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    /*public void onUseCard(AbstractCard card, UseCardAction action) {
        ShapeshifterMod.logger.info(AllForms.getAllForms().contains(card.cardID));
        ShapeshifterMod.logger.info(!uniqueForms.contains(card.cardID));
        ShapeshifterMod.logger.info(card.cardID);
        ShapeshifterMod.logger.info(uniqueForms);
        if(AllForms.getAllForms().contains(card.cardID) && !uniqueForms.contains(card.cardID)) {
            uniqueForms.add(card.cardID);
            this.addToBot(new GainEnergyAction(1));
            this.addToBot(new DrawCardAction(1));
        }
    }*/

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        //AbstractDungeon.actionManager.cardsPlayedThisCombat
        /*ArrayList<AbstractCard> allButMostRecentCard = new ArrayList<>(AbstractDungeon.actionManager.cardsPlayedThisCombat);
        allButMostRecentCard.remove(allButMostRecentCard.size() - 1);
        if(AllForms.getAllForms().contains(card.cardID) && !allButMostRecentCard.contains(card)) {
            this.addToBot(new GainEnergyAction(1));
            this.addToBot(new DrawCardAction(1));
        }*/
        if(AllForms.getAllForms().contains(card.cardID) && !uniqueForms.contains(card.cardID)) {
            uniqueForms.add(card.cardID);
            this.addToBot(new GainEnergyAction(1));
            this.addToBot(new DrawCardAction(1));
        }
    }


    /*@Override
    public void atStartOfTurn() { // At the start of your turn
        AbstractCard playCard = new DefaultRareAttack(); // Declare Card - the DefaultRareAttack card. We will name it 'playCard'.
        AbstractMonster targetMonster = AbstractDungeon.getRandomMonster(); // Declare Target - Random Monster. We will name the monster 'targetMonster'.

        playCard.freeToPlayOnce = true; //Self Explanatory

        if (playCard.type != AbstractCard.CardType.POWER) {
            playCard.purgeOnUse = true;
        }

        // Remove completely on use (Not Exhaust). A note - you don't need the '{}' in this if statement,
        // as it's just 1 line directly under. You can remove them, if you want. In fact, you can even put it all on 1 line:
        //  if (playCard.type != AbstractCard.CardType.POWER) playCard.purgeOnUse = true; - works identically

        AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(playCard, targetMonster)); // Play the card on the target.
    }*/

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
        return new DiverseSpiritPower(owner, source, amount);
    }
}

