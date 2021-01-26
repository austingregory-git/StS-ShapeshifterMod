package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

//public class ${NAME} extends AbstractDynamicCard {}

public class LeapingSlash extends AbstractDynamicCard {

    // TEXT DECLARATION

    //public static final String ID = ShapeshifterMod.makeID(${NAME}.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String ID = ShapeshifterMod.makeID(LeapingSlash.class.getSimpleName()); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("${NAME}.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 0;  // COST = ${COST}
    private static final int UPGRADED_COST = 2; // UPGRADED_COST = ${UPGRADED_COST}

    private static final int DAMAGE = 12;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 4;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}
    boolean canUse;

    // /STAT DECLARATION/


    public LeapingSlash() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else if(p.hasPower("Dexterity")) {
                AbstractPower dex = p.getPower("Dexterity");
                canUse = dex.amount >= 2;
                this.cantUseMessage = "Not enough Dexterity to use this card.";
                return canUse;
        } else {
            this.cantUseMessage = "Not enough Dexterity to use this card.";
            return false;
        }
    }

    public void triggerOnGlowCheck() {
        if(canUse) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}



