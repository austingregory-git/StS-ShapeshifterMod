package StSShapeShifter.cards;

import StSShapeShifter.actions.FlourishAction;
import StSShapeShifter.actions.UpgradeHandAction;
import StSShapeShifter.characters.ShapeShifter;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.TheDefault;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class Flourish extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(Flourish.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 2;
    //private static final int BLOCK = 12;
    //private static final int UPGRADE_PLUS_BLOCK = 3;


    // /STAT DECLARATION/


    public Flourish() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyFlourish();
    }

    public void applyFlourish() {
        for(int i=0; i<this.magicNumber; i++) {
            this.addToBot(new FlourishAction(upgraded, i));
            this.addToBot(new DrawCardAction(1, true));
        }
    }
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeMagicNumber(2);
            initializeDescription();
            this.rawDescription = "Draw !M! Cards. Reduce their cost to 0 this turn.";
        }
    }
}




