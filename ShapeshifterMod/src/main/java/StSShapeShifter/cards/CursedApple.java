package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.AllCurses;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;

import java.util.ArrayList;
import java.util.Random;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

//public class ${NAME} extends AbstractDynamicCard {}

public class CursedApple extends AbstractDynamicCard {

    // TEXT DECLARATION

    //public static final String ID = ShapeshifterMod.makeID(${NAME}.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String ID = ShapeshifterMod.makeID(CursedApple.class.getSimpleName()); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("card-art-generated/CursedApple.png");// "public static final String IMG = makeCardPath("${NAME}.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 1;  // COST = ${COST}
    private static final int UPGRADED_COST = 3; // UPGRADED_COST = ${UPGRADED_COST}

    private static final int DAMAGE = 20;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_MAGIC = 5;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}
    private int count = 0;

    // /STAT DECLARATION/


    public CursedApple() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.witherValue = this.baseWitherValue = 10;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyWither();
        count++;
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new ModifyDamageAction(this.uuid, -this.witherValue));
        updateBloomCount(-this.witherValue);
        int currVal = DAMAGE - (this.witherValue * count);
        if(currVal <= 0) {
            ArrayList<AbstractCard> curses = new ArrayList<AbstractCard>(AllCurses.getAllCursesCards());
            AbstractCard c = curses.get(new Random().nextInt(curses.size()));
            ShapeshifterMod.logger.info(c.cardID);
            this.addToBot(new AddCardToDeckAction(c.makeCopy()));
            this.exhaust = true;
        }
    }

    public void applyWither() {
        this.witherValue = this.baseWitherValue;
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeWitherValue(-5);
            initializeDescription();
        }
    }
}


