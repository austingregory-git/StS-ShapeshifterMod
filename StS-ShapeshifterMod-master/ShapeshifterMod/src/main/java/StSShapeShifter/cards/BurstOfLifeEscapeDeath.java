package StSShapeShifter.cards;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.BloomCountUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

//public class ${NAME} extends AbstractDynamicCard {}

public class BurstOfLifeEscapeDeath extends AbstractDynamicCard {

    // TEXT DECLARATION

    //public static final String ID = ShapeshifterMod.makeID(${NAME}.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String ID = ShapeshifterMod.makeID(BurstOfLifeEscapeDeath.class.getSimpleName()); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("card-art-generated/BurstOfLifeEscapeDeath.png");// "public static final String IMG = makeCardPath("${NAME}.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 0;

    // /STAT DECLARATION/


    public BurstOfLifeEscapeDeath() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        this.isMultiDamage = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.triggerOnGlowCheck();
        if(BloomCountUtils.getBloomCount() > 0) {
            calculateCardDamage(m);
            this.applyPowers();
            this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }
        else {
            ShapeshifterMod.logger.info(this.baseBlock);
            ShapeshifterMod.logger.info(this.block);
            this.baseBlock = Math.abs(BloomCountUtils.getBloomCount());
            this.applyPowersToBlock();
            ShapeshifterMod.logger.info(this.baseBlock);
            ShapeshifterMod.logger.info(this.block);
            this.addToBot(new GainBlockAction(p, this.block));
        }
        BloomCountUtils.setBloomCount(0);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        this.baseDamage = BloomCountUtils.getBloomCount();
        super.calculateCardDamage(mo);
    }

    @Override
    public void atTurnStart() {
        this.baseDamage = BloomCountUtils.getBloomCount();
        this.baseBlock = Math.abs(BloomCountUtils.getBloomCount());
        super.atTurnStart();
    }


    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            canUse = BloomCountUtils.getBloomCount() != 0;
            if(!canUse)
                this.cantUseMessage = "Your Bloom Count cannot be 0.";
            return canUse;
        }
    }

    public void triggerOnGlowCheck() {
        if (BloomCountUtils.getBloomCount() > 0) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}





