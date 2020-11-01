package StSShapeShifter.cards;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.BloomCountUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

//public class ${NAME} extends AbstractDynamicCard {}

public class SunflowerShuriken extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(SunflowerShuriken.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 1;  // COST = ${COST}
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = ${UPGRADED_COST}

    private static final int DAMAGE = 12;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 4;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}


    // /STAT DECLARATION/


    public SunflowerShuriken() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.witherValue = this.baseWitherValue = 4;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyWither();
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(BloomCountUtils.getBloomCount() <= -20) {
            if(upgraded)
                this.addToBot(new ModifyDamageAction(this.uuid, 22-this.baseDamage));
            else
                this.addToBot(new ModifyDamageAction(this.uuid, 18-this.baseDamage));
            this.applyPowers();
        }
        else if(BloomCountUtils.getBloomCount() <= -10) {
            if(upgraded)
                this.addToBot(new ModifyDamageAction(this.uuid, DAMAGE+UPGRADE_PLUS_DMG-this.baseDamage));
            else
                this.addToBot(new ModifyDamageAction(this.uuid, DAMAGE-this.baseDamage));

            this.applyPowers();
        }
        else {
            this.addToBot(new ModifyDamageAction(this.uuid, -this.witherValue));
            updateBloomCount(-this.witherValue);
        }


    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.rawDescription = "Deal !D! damage. If you are Decaying, set this card's damage to 22. If you are Wilting, Reset this card's damage. Otherwise, Wither !StSShapeShifter:Wither!.";
            initializeDescription();
        }
    }
}


