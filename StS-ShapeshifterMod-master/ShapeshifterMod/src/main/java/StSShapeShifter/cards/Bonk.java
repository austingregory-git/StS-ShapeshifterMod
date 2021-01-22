package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.FreeFormPower;
import StSShapeShifter.powers.FullMoonPower;
import StSShapeShifter.util.AllForms;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;

import java.util.ArrayList;
import java.util.Random;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

//public class ${NAME} extends AbstractDynamicCard {}

public class Bonk extends AbstractDynamicCard {

    // TEXT DECLARATION

    //public static final String ID = ShapeshifterMod.makeID(${NAME}.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String ID = ShapeshifterMod.makeID(Bonk.class.getSimpleName()); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("card-art-generated/Bonk.png");// "public static final String IMG = makeCardPath("${NAME}.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 2;  // COST = ${COST}
    private static final int UPGRADED_COST = 2; // UPGRADED_COST = ${UPGRADED_COST}

    private static final int DAMAGE = 10;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 4;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    // /STAT DECLARATION/


    public Bonk() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        ArrayList<AbstractCard> forms = new ArrayList<AbstractCard>(AllForms.getAllFormsCards());
        AbstractCard c = forms.get(new Random().nextInt(forms.size()));
        if(c instanceof AbstractShapeShifterCard) {
            ((AbstractShapeShifterCard) c).ModifiedCostCode = 2;
        }
        ShapeshifterMod.logger.info(c.cardID);
        c.costForTurn = 0;
        c.exhaust = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, m));

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



