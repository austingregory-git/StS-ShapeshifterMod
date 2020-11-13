package StSShapeShifter.cards;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.BloomCountUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

//public class ${NAME} extends AbstractDynamicCard {}

public class BladeOfGrass extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(BladeOfGrass.class.getSimpleName());
    public static final String IMG = makeCardPath("card-art-generated/BladeOfGrass.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 1;  // COST = ${COST}
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = ${UPGRADED_COST}

    private static final int DAMAGE = 8;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}
    private static final int UPGRADE_PLUS_GROW = 1;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}


    // /STAT DECLARATION/


    public BladeOfGrass() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.growValue = this.baseGrowValue = 2;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyGrow();
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(BloomCountUtils.getBloomCount() >= 20) {
            int mod = this.growValue * 4;
            this.addToBot(new ModifyDamageAction(this.uuid, mod));
            updateBloomCount(mod);
        }
        else if(BloomCountUtils.getBloomCount() >= 10) {
            int mod = this.growValue * 2;
            this.addToBot(new ModifyDamageAction(this.uuid, mod));
            updateBloomCount(mod);
        }
        else {
            this.addToBot(new ModifyDamageAction(this.uuid, this.growValue));
            updateBloomCount(this.growValue);
        }


    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeGrowValue(UPGRADE_PLUS_GROW);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}


