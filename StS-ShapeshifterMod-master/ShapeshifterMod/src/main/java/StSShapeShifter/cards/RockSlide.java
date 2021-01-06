package StSShapeShifter.cards;

import StSShapeShifter.actions.RockSlideAction;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.BloomCountUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;

import java.util.ArrayList;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

//public class ${NAME} extends AbstractDynamicCard {}

public class RockSlide extends AbstractDynamicCard {

    // TEXT DECLARATION

    //public static final String ID = ShapeshifterMod.makeID(${NAME}.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String ID = ShapeshifterMod.makeID(RockSlide.class.getSimpleName()); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("${NAME}.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 1;  // COST = ${COST}
    private static final int UPGRADED_COST = 2; // UPGRADED_COST = ${UPGRADED_COST}

    private static final int DAMAGE = 5;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 1;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}
    private static final int MAGIC = 1;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}
    private static final int UPGRADE_PLUS_MAGIC = 1;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}
    public int count = 0;

    // /STAT DECLARATION/


    public RockSlide() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.growValue = this.baseGrowValue = 1;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //applyGrow();
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new ModifyDamageAction(this.uuid, this.growValue));
        updateBloomCount(this.growValue);


    }

    public void onMoveToDiscard() {
        this.addToBot(new RockSlideAction(this));
    }
    
    /*public void onMoveToDiscard() {
        //this.addToBot(new RockSlideAction(this.uuid, this));
        ShapeshifterMod.logger.info(!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty());
        if(!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            ArrayList<AbstractCard> cardsThisTurn = new ArrayList<>(AbstractDungeon.actionManager.cardsPlayedThisTurn);
            ShapeshifterMod.logger.info(cardsThisTurn);
            ShapeshifterMod.logger.info(cardsThisTurn.contains(this));
            if(cardsThisTurn.contains(this)) {
                count++;
                cardsThisTurn.remove(cardsThisTurn.size() - 1);
                if(!cardsThisTurn.contains(this) && count == 1) {
                    AbstractDungeon.player.hand.addToHand(this);
                    AbstractDungeon.player.discardPile.removeCard(this);
                    this.applyGrow();
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.discardPile.refreshHandLayout();
                }
            }
        }
    }*/

    @Override
    public void atTurnStart() {
        count = 0;
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeGrowValue(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}



