package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.AllForms;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.TheDefault;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

//public class ${NAME} extends AbstractDynamicCard {}

public class MultiSpiritedStrike extends AbstractDynamicCard {

    // TEXT DECLARATION

    //public static final String ID = ShapeshifterMod.makeID(${NAME}.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String ID = ShapeshifterMod.makeID("MultiSpiritedStrike"); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("${NAME}.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 1;  // COST = ${COST}
    //private static final int UPGRADED_COST = 3; // UPGRADED_COST = ${UPGRADED_COST}

    private static final int DAMAGE = 0;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_MAGIC = 1;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}


    // /STAT DECLARATION/


    public MultiSpiritedStrike() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    // Actions the card should do.
    //TODO
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cards = new ArrayList<>(AbstractDungeon.actionManager.cardsPlayedThisCombat);
        int numFormsPlayed = 0;
        for (AbstractCard c : cards) {
            if (AllForms.getAllForms().contains(c.cardID))
                numFormsPlayed++;
        }
        this.baseDamage = numFormsPlayed * this.magicNumber;
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    /*public void calculateCardDamage(AbstractMonster mo) {
        ArrayList<AbstractCard> cards = new ArrayList<>(AbstractDungeon.actionManager.cardsPlayedThisCombat);

        for (AbstractCard c : cards) {
            if (AllForms.getAllForms().contains(c.cardID))
                numFormsPlayed++;
        }
        this.damage = numFormsPlayed * this.magicNumber;
        super.calculateCardDamage(mo);

    }*/


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}


